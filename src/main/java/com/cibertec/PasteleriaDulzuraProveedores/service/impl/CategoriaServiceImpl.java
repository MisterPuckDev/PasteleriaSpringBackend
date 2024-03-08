package com.cibertec.PasteleriaDulzuraProveedores.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.PasteleriaDulzuraProveedores.model.Categoria;
import com.cibertec.PasteleriaDulzuraProveedores.model.Proveedor;
import com.cibertec.PasteleriaDulzuraProveedores.repo.CategoriaRepo;
import com.cibertec.PasteleriaDulzuraProveedores.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	CategoriaRepo categoriaRepo;

	@Override
	public Categoria insertaActualizaCategoria(Categoria categoria) {
	
		return categoriaRepo.save(categoria);
	}

	@Override
	public List<Categoria> listaCategoriaPorNombreLike(String nombre) {
	
		return categoriaRepo.listaPorNombreLike(nombre);
	}
	
	public List<Categoria> listarTodasLasCategorias() {
        return categoriaRepo.findAll();
    }

	@Override
	public void eliminaCategoria(int categoriaId) {
		categoriaRepo.deleteById(categoriaId);
		
	}

	@Override
	public List<Categoria> listaPorNombreIgualRegistra(String nombre) {
		return categoriaRepo.listaPorNombreIgualRegistra(nombre);
	}

	@Override
	public List<Categoria> listaPorDescripcionIgualRegistra(String descripcion) {
		return categoriaRepo.listaPorDescripcionIgualRegistra(descripcion);
	}

	@Override
	public Categoria obtienePorId(int categoriaId) {
		 Optional<Categoria> optionalCategoria = categoriaRepo.findById(categoriaId);
	        if (optionalCategoria.isPresent()) {
	            return optionalCategoria.get();
	        } else {
	            return null; // O manejarlo de otra manera, dependiendo de tu lógica de negocio
	        }
	}

	@Override
	public List<Categoria> listaPorNombreIgualActualiza(String nombre) {
		return categoriaRepo.listaPorNombreIgualActualiza(nombre);
	}

	@Override
	public List<Categoria> listaDescripcionIgualActualiza(String descripcion) {
		return categoriaRepo.listaPorDescripcionIgualActualiza(descripcion);
	}

	@Override
	public List<Categoria> listaConsulta(String nombre, String descripcion) {
		return categoriaRepo.listaConsulta(nombre, descripcion);
	}

	@Override
	public List<Categoria> listaPorDescripcionIgualActualiza(String descripcion, int categoriaId) {
		return categoriaRepo.listaPorDescripcionIgualActualiza(descripcion, categoriaId);
	}



}
