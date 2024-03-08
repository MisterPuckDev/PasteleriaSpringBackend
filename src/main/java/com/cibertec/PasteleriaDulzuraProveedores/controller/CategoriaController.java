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

import com.cibertec.PasteleriaDulzuraProveedores.model.Categoria;
import com.cibertec.PasteleriaDulzuraProveedores.service.CategoriaService;
import com.cibertec.PasteleriaDulzuraProveedores.util.Constantes;

@RestController
@RequestMapping("/url/crudCategoria")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/listaCategoriaPorNombreLike/{nombre}")
    @ResponseBody
    public ResponseEntity<List<Categoria>> listaCategoriaPorNombreLike(@PathVariable("nombre") String nombre) {
        List<Categoria> lista = null;
        try {
        	if(nombre.equals("todos")) {
        		lista = service.listaCategoriaPorNombreLike("%");
        	}else{
        		lista = service.listaCategoriaPorNombreLike("%" + nombre + "%");
        	}
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/listaCategoria")
    @ResponseBody
    public ResponseEntity<List<Categoria>> listarTodasLasCategorias() {
        List<Categoria> lista = service.listarTodasLasCategorias(); // Asegúrate de que este método exista en tu servicio
        return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/detalleCategoria/{id}")
    @ResponseBody
    public ResponseEntity<Categoria> obtieneCategoriaPorId(@PathVariable("id") int categoriaId) {
    	Categoria categoria = null;
        try {
        	categoria = service.obtienePorId(categoriaId); // Asumiendo que este método ya existe en tu servicio
            if (categoria == null) {
                return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra el proveedor
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build(); // Retorna 500 si ocurre un error
        }
        return ResponseEntity.ok(categoria); // Retorna 200 con el proveedor si se encuentra
    }


    @PostMapping("/registraCategoria")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> insertaCategoria(@RequestBody Categoria obj) {
        Map<String, Object> salida = new HashMap<>();
        try {
            obj.setCategoriaId(0); // Asegura la creación
            
            // Validación de Nombre unique
            List<Categoria> lstCategoriaNombre = service.listaPorNombreIgualRegistra(obj.getNombre());
            if (!lstCategoriaNombre.isEmpty()) {
                salida.put("mensaje", "La Categoria con el nombre " + obj.getNombre() + " ya existe");
                return ResponseEntity.badRequest().body(salida);
            }
                            
            // Validación de Descripcion unique
            List<Categoria> lstCategoriaUnique = service.listaPorDescripcionIgualRegistra(obj.getDescripcion());
            if (!lstCategoriaUnique.isEmpty()) {
                salida.put("mensaje", "La Descripción " + obj.getDescripcion() + " ya existe");
                return ResponseEntity.badRequest().body(salida); // Cambio aquí para error
            }

            Categoria objSalida = service.insertaActualizaCategoria(obj);
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

    @PutMapping("/actualizaCategoria")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> actualizaCategoria(@RequestBody Categoria obj) {
        Map<String, Object> salida = new HashMap<>();

        try {
            Categoria categoriaActual = service.obtienePorId(obj.getCategoriaId());
            if (categoriaActual == null) {
                salida.put("mensaje", "La categoría con ID " + obj.getCategoriaId() + " no existe");
                return ResponseEntity.badRequest().body(salida);
            }

            // Verifica si el nombre de la categoría ha cambiado y si es único
            if (!obj.getNombre().equals(categoriaActual.getNombre())) {
                List<Categoria> lstCategoriaNombre = service.listaPorNombreIgualActualiza(obj.getNombre());
                if (!lstCategoriaNombre.isEmpty()) {
                    salida.put("mensaje", "La categoría con el nombre " + obj.getNombre() + " ya existe");
                    return ResponseEntity.badRequest().body(salida);
                }
            }

         // Verifica si la descripción de la categoría ha cambiado y si es única
            if (!obj.getDescripcion().equals(categoriaActual.getDescripcion())) {
                List<Categoria> lstCategoriaDescripcion = service.listaPorDescripcionIgualActualiza(obj.getDescripcion(), obj.getCategoriaId());
                if (!lstCategoriaDescripcion.isEmpty()) {
                    salida.put("mensaje", "La categoría con la descripción " + obj.getDescripcion() + " ya existe");
                    return ResponseEntity.badRequest().body(salida);
                }
            }
            
            Categoria objSalida = service.insertaActualizaCategoria(obj);
            if (objSalida == null) {
                salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
            } else {
                salida.put("mensaje", Constantes.MENSAJE_ACT_EXITOSO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
            return ResponseEntity.internalServerError().body(salida);
        }
        return ResponseEntity.ok(salida);
    }
	

	@DeleteMapping("/eliminaCategoria/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaCategoria(@PathVariable("id") int categoriaId) {
		Map<String, Object> salida = new HashMap<>();
		try {
			service.eliminaCategoria(categoriaId);
			salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
}