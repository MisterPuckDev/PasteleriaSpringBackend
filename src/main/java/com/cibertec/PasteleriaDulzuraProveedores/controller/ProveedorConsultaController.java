package com.cibertec.PasteleriaDulzuraProveedores.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.PasteleriaDulzuraProveedores.model.Proveedor;
import com.cibertec.PasteleriaDulzuraProveedores.service.ProveedorService;
import com.cibertec.PasteleriaDulzuraProveedores.util.UtilExcel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.apachecommons.CommonsLog;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@RestController
@RequestMapping("/url/consultaProveedor")
@CrossOrigin(origins = "http://localhost:4200")
@CommonsLog
public class ProveedorConsultaController {
	
	@Autowired
	private ProveedorService proveedorService;
	
	@ResponseBody
	@GetMapping("/consultaProveedorPorParametros")
	public List<Proveedor> listaConsultaProveedor(
								@RequestParam(name = "razonSocial" , required = false , defaultValue = "") String razonSocial, 
								@RequestParam(name = "ruc" , required = false , defaultValue = "") String ruc, 
								@RequestParam(name = "estado" , required = false , defaultValue = "1") int estado){
		
		List<Proveedor> lstSalida = proveedorService.listaConsulta(razonSocial, ruc, estado);
		return lstSalida;
	}
	
	@PostMapping("/reporteProveedorPdf")
	public void  exportaPDF(
			@RequestParam(name = "ruc" , required = false , defaultValue = "") String ruc, 
			@RequestParam(name = "razonSocial" , required = false , defaultValue = "") String razonSocial,			
			@RequestParam(name = "estado" , required = false , defaultValue = "1") int estado,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		try {
			//PASO 1 Fuente de datos
			List<Proveedor> lstSalida = proveedorService.listaConsulta("%"+razonSocial+"%", ruc, estado);
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstSalida);
	         
			//PASO 2 Diseño de reporte
			String fileReporte  = request.getServletContext().getRealPath("/WEB-INF/reportes/reporteProveedor.jasper");
			log.info(">>> fileReporte >> " + fileReporte);
			
			//PASO3 parámetros adicionales
			String fileLogo  = request.getServletContext().getRealPath("/WEB-INF/img/logo.jpg");
			log.info(">>> fileLogo >> " + fileLogo);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("RUTA_LOGO", fileLogo);

			
			//PASO4 Se juntas la data, diseño y parámetros
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new FileInputStream(new File(fileReporte)));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
	       
			//PASO 5 parametros en el Header del mensajes HTTP
    		response.setContentType("application/pdf");
    	    response.addHeader("Content-disposition", "attachment; filename=ReporteAutor.pdf");
		    
			//PASO 6 Se envia el pdf
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static String[] HEADERs = {"CÓDIGO", "RUC", "RAZÓN SOCIAL", "ESTADO", "FECHA REGISTRO"};
	private static String SHEET = "Listado";
	private static String TITLE = "Listado de proveedores";
	private static int[] HEADER_WITH = { 3000, 10000, 6000, 10000, 20000, 10000 };
	
	@PostMapping("/reporteProveedorExcel")
	public void  exportaExcel(
			@RequestParam(name = "ruc" , required = false , defaultValue = "") String ruc, 
			@RequestParam(name = "razonSocial" , required = false , defaultValue = "") String razonSocial,			
			@RequestParam(name = "estado" , required = false , defaultValue = "1") int estado,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		Workbook excel  = null;
		try {
			//Se crear el Excel
			excel = new XSSFWorkbook();
			
			//Se crear la hoja del Excel
			Sheet hoja = excel.createSheet(SHEET);
			
			hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, HEADER_WITH.length - 1));

			//Se establece el ancho de las columnas
			for (int i = 0; i < HEADER_WITH.length; i++) {
				hoja.setColumnWidth(i, HEADER_WITH[i]);
			}
			
			CellStyle estiloHeadCentrado = UtilExcel.setEstiloHeadCentrado(excel);
			CellStyle estiloHeadIzquierda = UtilExcel.setEstiloHeadIzquierda(excel);
			CellStyle estiloNormalCentrado = UtilExcel.setEstiloNormalCentrado(excel);
			CellStyle estiloNormalIzquierda = UtilExcel.setEstiloNormalIzquierdo(excel);

			
			//Fila 0
			Row fila1 = hoja.createRow(0);
			Cell celAuxs = fila1.createCell(0);
			celAuxs.setCellStyle(estiloHeadIzquierda);
			celAuxs.setCellValue(TITLE);
			
			//Fila 1
			Row fila2 = hoja.createRow(1);
			Cell celAuxs2 = fila2.createCell(0);
			celAuxs2.setCellValue("");
			
			//Fila 2
			Row fila3 = hoja.createRow(2);
			for (int i = 0; i < HEADERs.length; i++) {
				Cell celda1 = fila3.createCell(i);
				celda1.setCellStyle(estiloHeadCentrado);
				celda1.setCellValue(HEADERs[i]);
			}
			
			List<Proveedor> lstSalida = proveedorService.listaConsulta("%"+ruc+"%", razonSocial, estado);
			//Fila 3....n
			int rowIdx = 3;
			for (Proveedor obj : lstSalida) {
				Row row = hoja.createRow(rowIdx++);

				Cell cel0 = row.createCell(0);
				cel0.setCellValue(obj.getProveedorId());
				cel0.setCellStyle(estiloNormalCentrado);

				Cell cel1 = row.createCell(1);
				cel1.setCellValue(obj.getRuc());
				cel1.setCellStyle(estiloNormalIzquierda);
				
				Cell cel2 = row.createCell(2);
				cel2.setCellValue(obj.getRazonSocial());
				cel2.setCellStyle(estiloNormalCentrado);
				
				Cell cel3 = row.createCell(3);
				cel3.setCellValue(obj.getReporteEstado());
				cel3.setCellStyle(estiloNormalCentrado);
				
				Cell cel4 = row.createCell(4);
				cel4.setCellValue(obj.getReporteFecha());
				cel4.setCellStyle(estiloNormalCentrado);
			}
			
			response.setContentType("application/vnd.ms-excel");
    	    response.addHeader("Content-disposition", "attachment; filename=ReporteProveedor.xlsx");
    	    
    	    OutputStream outStream = response.getOutputStream();
    	    excel.write(outStream);
    	    outStream.close();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (excel != null)
				excel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}