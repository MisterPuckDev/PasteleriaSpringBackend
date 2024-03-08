package com.cibertec.PasteleriaDulzuraProveedores.service;

import java.util.List;

import com.cibertec.PasteleriaDulzuraProveedores.model.Categoria;
import com.cibertec.PasteleriaDulzuraProveedores.model.Proveedor;

public interface CategoriaService {
	
	        //Para el Crud
			public abstract Categoria insertaActualizaCategoria(Categoria categoria);
			public abstract List<Categoria> listaCategoriaPorNombreLike(String nombre);
			public abstract void eliminaCategoria(int categoriaId);

			// Validaciones CRUD Registrar
			public abstract List<Categoria> listaPorNombreIgualRegistra(String nombre);
			public abstract List<Categoria> listaPorDescripcionIgualRegistra(String descripcion);

			// Validaciones CRUD Actualizar
			public abstract Categoria obtienePorId(int categoriaId);
			public abstract List<Categoria> listaPorNombreIgualActualiza(String nombre);
			public abstract List<Categoria> listaDescripcionIgualActualiza(String descripcion);
			
			// Agrega la definición del método para validar descripciones únicas al actualizar
		    List<Categoria> listaPorDescripcionIgualActualiza(String descripcion, int categoriaId);
		    
			
			//Consulta
			public abstract List<Categoria> listaConsulta(String nombre, String descripcion);
			
			// Método para listar todas las categorías
		    List<Categoria> listarTodasLasCategorias();
		    

}
