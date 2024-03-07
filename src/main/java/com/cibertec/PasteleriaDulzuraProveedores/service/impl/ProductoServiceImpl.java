package com.cibertec.PasteleriaDulzuraProveedores.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.PasteleriaDulzuraProveedores.model.Producto;
import com.cibertec.PasteleriaDulzuraProveedores.repo.ProductoRepo;
import com.cibertec.PasteleriaDulzuraProveedores.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	ProductoRepo productoRepo;

	@Override
	public List<Producto> listProducto() {
		return productoRepo.findAll();
	}

	@Override
	public Producto add(Producto p) {
		return productoRepo.save(p);
	}

	@Override
	public Producto update(Producto p) {
		return productoRepo.save(p);
	}

	@Override
	public void delete(int id) {
		productoRepo.deleteById(id);
		
	}

}