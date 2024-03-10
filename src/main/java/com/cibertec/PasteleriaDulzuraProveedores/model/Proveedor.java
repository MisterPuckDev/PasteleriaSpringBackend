package com.cibertec.PasteleriaDulzuraProveedores.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "proveedor")
public class Proveedor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int proveedorId;
	private String ruc;
	private String razonSocial;
	private String direccion;
	private String telefono;
	private String correo;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "suministroId")
	private Suministro suministro;	

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaRegistro;  
    

	private int estado;		
	
	
	// Métodos de reporte
    public String getReporteEstado() {
        return estado == 1 ? "Activo" : "Inactivo";
    }  

    public String getReporteFecha() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	return sdf.format(fechaRegistro);
    }
    
}
