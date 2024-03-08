package com.cibertec.PasteleriaDulzuraProveedores.controller;


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

import com.cibertec.PasteleriaDulzuraProveedores.model.Producto;
import com.cibertec.PasteleriaDulzuraProveedores.service.ProductoService;
import com.cibertec.PasteleriaDulzuraProveedores.util.Constantes;

@RestController
@RequestMapping("/url/crudProducto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {
	
	@Autowired
	ProductoService productoService;

	 @GetMapping("/listaProductoPorNombreLike/{nombre}")
	    @ResponseBody
	    public ResponseEntity<List<Producto>> listaProductoPorNombreLike(@PathVariable("nombre") String nombre) {
	        List<Producto> lista = null;
	        try {
	        	if(nombre.equals("todos")) {
	        		lista = productoService.listaProductoPorNombreLike("%");
	        	}else{
	        		lista = productoService.listaProductoPorNombreLike("%" + nombre + "%");
	        	}
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return ResponseEntity.ok(lista);
	    }
	    
	    @GetMapping("/detalleProducto/{id}")
	    @ResponseBody
	    public ResponseEntity<Producto> obtieneProductoPorId(@PathVariable("id") int productoId) {
	    	Producto producto = null;
	        try {
	            producto = productoService.obtienePorId(productoId); // Asumiendo que este método ya existe en tu servicio
	            if (producto == null) {
	                return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra el proveedor
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.internalServerError().build(); // Retorna 500 si ocurre un error
	        }
	        return ResponseEntity.ok(producto); // Retorna 200 con el proveedor si se encuentra
	    }


	    @PostMapping("/registraProducto")
	    @ResponseBody
	    public ResponseEntity<Map<String, Object>> insertaProducto(@RequestBody Producto obj) {
	        Map<String, Object> salida = new HashMap<>();
	        try {
	            obj.setProductoId(0); // Asegura la creación
	            
	            // Validación de Nombre unique
	            List<Producto> lstProductoNombre = productoService.listaPorNombreIgualRegistra(obj.getNombre());
	            if (!lstProductoNombre.isEmpty()) {
	                salida.put("mensaje", "El Producto con el nombre " + obj.getNombre() + " ya existe");
	                return ResponseEntity.badRequest().body(salida);
	            }
	                            

	            Producto objSalida = productoService.insertaActualizaProducto(obj);
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

	    @PutMapping("/actualizaProducto")
	    @ResponseBody
	    public ResponseEntity<Map<String, Object>> actualizaProducto(@RequestBody Producto obj) {
	        Map<String, Object> salida = new HashMap<>();
	        
	        try {
	            // Busca el producto actual en la base de datos
	            Producto productoActual = productoService.obtienePorId(obj.getProductoId());
	            if (productoActual == null) {
	                salida.put("mensaje", "El Producto con ID " + obj.getProductoId() + " no existe");
	                return ResponseEntity.badRequest().body(salida);
	            }

	            // Si el nombre ha cambiado, verifica que el nuevo nombre no esté ya en uso
	            if (!obj.getNombre().equals(productoActual.getNombre())) {
	                List<Producto> lstProductoNombre = productoService.listaPorNombreIgualActualiza(obj.getNombre());
	                if (!lstProductoNombre.isEmpty()) {
	                    salida.put("mensaje", "El Producto con el nombre " + obj.getNombre() + " ya existe");
	                    return ResponseEntity.badRequest().body(salida);
	                }
	            }
	            
	            // Actualiza el producto
	            Producto objSalida = productoService.insertaActualizaProducto(obj);
	            if (objSalida == null) {
	                salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
	            } else {
	                salida.put("mensaje", Constantes.MENSAJE_ACT_EXITOSO);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
	            return ResponseEntity.internalServerError().body(salida); // Manejo de error interno
	        }
	        return ResponseEntity.ok(salida);
	    }
		
		
		@DeleteMapping("/eliminaProducto/{id}")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> eliminaProducto(@PathVariable("id") int productoId) {
			Map<String, Object> salida = new HashMap<>();
			try {
				productoService.eliminaProducto(productoId);
				salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
			}
			return ResponseEntity.ok(salida);
		}
	
}