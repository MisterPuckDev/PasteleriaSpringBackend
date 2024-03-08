package com.cibertec.PasteleriaDulzuraProveedores.service;

import java.util.List;

import com.cibertec.PasteleriaDulzuraProveedores.model.Producto;


public interface ProductoService {
	
	//Para el Crud
			public abstract Producto insertaActualizaProducto(Producto producto);
			public abstract List<Producto> listaProductoPorNombreLike(String nombre);
			public abstract void eliminaProducto(int productoId);

			// Validaciones CRUD Registrar
			public abstract List<Producto> listaPorNombreIgualRegistra(String nombre);
			
			// Validaciones CRUD Actualizar
			public abstract Producto obtienePorId(int productoId);
			public abstract List<Producto> listaPorNombreIgualActualiza(String nombre);
			
			//Consulta
			public abstract List<Producto> listaConsulta(String nombre);
			
			// Método nuevo para validar al actualizar (excluyendo un productoId específico)
		    public abstract List<Producto> listaPorNombreExcluyendoProductoId(String nombre, Integer productoId);
		    

}
