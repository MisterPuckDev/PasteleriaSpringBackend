package com.cibertec.PasteleriaDulzuraProveedores.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.PasteleriaDulzuraProveedores.model.Proveedor;
import com.cibertec.PasteleriaDulzuraProveedores.service.ProveedorService;
import com.cibertec.PasteleriaDulzuraProveedores.util.Constantes;

@RestController
@RequestMapping("/url/crudProveedor")
@CrossOrigin(origins = "http://localhost:4200")
public class ProveedorCrudController {

    @Autowired
    private ProveedorService service;

    @GetMapping("/listaProveedorPorRazonSocialLike/{razonSocial}")
    @ResponseBody
    public ResponseEntity<List<Proveedor>> listaProveedorPorRazonSocialLike(@PathVariable("razonSocial") String razonSocial) {
        List<Proveedor> lista = null;
        try {
        	if(razonSocial.equals("todos")) {
        		lista = service.listaProveedorPorRazonSocialLike("%");
        	}else{
        		lista = service.listaProveedorPorRazonSocialLike("%" + razonSocial + "%");
        	}
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/detalleProveedor/{id}")
    @ResponseBody
    public ResponseEntity<Proveedor> obtieneProveedorPorId(@PathVariable("id") int proveedorId) {
        Proveedor proveedor = null;
        try {
            proveedor = service.obtienePorId(proveedorId); // Asumiendo que este método ya existe en tu servicio
            if (proveedor == null) {
                return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra el proveedor
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build(); // Retorna 500 si ocurre un error
        }
        return ResponseEntity.ok(proveedor); // Retorna 200 con el proveedor si se encuentra
    }


    @PostMapping("/registraProveedor")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> insertaProveedor(@RequestBody Proveedor obj) {
        Map<String, Object> salida = new HashMap<>();
        try {
            obj.setProveedorId(0); // Asegura la creación
            obj.setFechaRegistro(new Date(System.currentTimeMillis())); // Fecha actual
            obj.setEstado(1); // Estado activo
            
            // Validación de RazonSocial unique
            List<Proveedor> lstProveedorRazonSocial = service.listaPorRazonSocialIgualRegistra(obj.getRazonSocial());
            if (!lstProveedorRazonSocial.isEmpty()) {
                salida.put("mensaje", "El Proveedor con la razón social " + obj.getRazonSocial() + " ya existe");
                return ResponseEntity.badRequest().body(salida);
            }
                            
            // Validación de RUC unique
            List<Proveedor> lstProveedorUnique = service.listaPorRUCIgualRegistra(obj.getRuc());
            if (!lstProveedorUnique.isEmpty()) {
                salida.put("mensaje", "El RUC " + obj.getRuc() + " ya existe");
                return ResponseEntity.badRequest().body(salida); // Cambio aquí para error
            }

            Proveedor objSalida = service.insertaActualizaProveedor(obj);
            if (objSalida == null) {
                salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
            } else {
                salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
            return ResponseEntity.internalServerError().body(salida); // Manejo de error interno
        }
        return ResponseEntity.ok(salida); // Respuesta de éxito
    }

	@PutMapping("/actualizaProveedor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaProveedor(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		
		//Descomentar para la PC02
		//obj.setFechaActualizacion(new Date());
		
		//Validación de RazonSocial unique
		List<Proveedor> lstProveedorRazonSocial =  service.listaPorRazonSocialIgualActualiza(obj.getRazonSocial());
		if (!lstProveedorRazonSocial.isEmpty()) {
			salida.put("mensaje", "El Proveedor " + obj.getRazonSocial() + " ya existe");
			return ResponseEntity.badRequest().body(salida);
		}		
		
		
		try {
			obj.setFechaRegistro(new Date(System.currentTimeMillis())); // Fecha actual
			Proveedor objSalida =  service.insertaActualizaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", Constantes.MENSAJE_ACT_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualizaEstadoProveedor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaEstadoProveedor(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		
		
		try {
			obj.setFechaRegistro(new Date(System.currentTimeMillis())); // Fecha actual
			Proveedor objSalida =  service.insertaActualizaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", Constantes.MENSAJE_ACT_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	
	@DeleteMapping("/eliminaProveedor/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaProveedor(@PathVariable("id") int proveedorId) {
		Map<String, Object> salida = new HashMap<>();
		try {
			service.eliminaProveedor(proveedorId);
			salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
}







