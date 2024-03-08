package com.cibertec.PasteleriaDulzuraProveedores.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.PasteleriaDulzuraProveedores.model.Producto;
import com.cibertec.PasteleriaDulzuraProveedores.model.Proveedor;
import com.cibertec.PasteleriaDulzuraProveedores.repo.ProductoRepo;
import com.cibertec.PasteleriaDulzuraProveedores.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	ProductoRepo productoRepo;

	@Override
	public Producto insertaActualizaProducto(Producto producto) {
		return productoRepo.save(producto);
	}

	@Override
	public List<Producto> listaProductoPorNombreLike(String nombre) {
		return productoRepo.listaPorNombreLike(nombre);
	}

	@Override
	public void eliminaProducto(int productoId) {
		productoRepo.deleteById(productoId);
		
	}

	@Override
	public List<Producto> listaPorNombreIgualRegistra(String nombre) {
		return productoRepo.listaPorNombreIgualRegistra(nombre);
	}

	@Override
	public Producto obtienePorId(int productoId) {
		 Optional<Producto> optionalProducto = productoRepo.findById(productoId);
	        if (optionalProducto.isPresent()) {
	            return optionalProducto.get();
	        } else {
	            return null; // O manejarlo de otra manera, dependiendo de tu lógica de negocio
	        }
	}

	@Override
	public List<Producto> listaPorNombreIgualActualiza(String nombre) {
		return productoRepo.listaPorNombreIgualActualiza(nombre);
	}

	@Override
	public List<Producto> listaConsulta(String nombre) {
		return productoRepo.listaConsulta(nombre);
	}

	@Override
	public List<Producto> listaPorNombreExcluyendoProductoId(String nombre, Integer productoId) {
		return productoRepo.findByNombreAndProductoIdNot(nombre, productoId);
	}



}