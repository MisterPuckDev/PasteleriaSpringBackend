package com.cibertec.PasteleriaDulzuraProveedores.service;

import java.util.List;

import com.cibertec.PasteleriaDulzuraProveedores.model.Proveedor;

public interface ProveedorService {
	
	//Para el Crud
		public abstract Proveedor insertaActualizaProveedor(Proveedor proveedor);
		public abstract List<Proveedor> listaProveedorPorRazonSocialLike(String razonSocial);
		public abstract void eliminaProveedor(int proveedorId);

		// Validaciones CRUD Registrar
		public abstract List<Proveedor> listaPorRazonSocialIgualRegistra(String razonSocial);
		public abstract List<Proveedor> listaPorRUCIgualRegistra(String ruc);

		// Validaciones CRUD Actualizar
		public abstract Proveedor obtienePorId(int proveedorId);
		public abstract List<Proveedor> listaPorRazonSocialIgualActualiza(String razonSocial);
		public abstract List<Proveedor> listaPorRUCIgualActualiza(String ruc);
		
		//Consulta
		public abstract List<Proveedor> listaConsulta(String razonSocial, String ruc, int estado);
		
		// Método para listar todas las categorías
	    List<Proveedor> listarTodosLosProveedores();
		
	}
