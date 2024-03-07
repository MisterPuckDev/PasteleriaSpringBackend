package com.cibertec.PasteleriaDulzuraProveedores.service;

import java.util.List;

import com.cibertec.PasteleriaDulzuraProveedores.model.Producto;

public interface ProductoService {
	
	public List<Producto> listProducto();
	public Producto add(Producto p);
	public Producto update(Producto p);
	public void delete(int id);

}
