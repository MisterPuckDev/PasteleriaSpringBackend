package com.cibertec.PasteleriaDulzuraProveedores.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.PasteleriaDulzuraProveedores.model.Categoria;
import com.cibertec.PasteleriaDulzuraProveedores.repo.CategoriaRepo;
import com.cibertec.PasteleriaDulzuraProveedores.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	CategoriaRepo categoriaRepo;

	@Override
	public List<Categoria> listCategoria() {
		return categoriaRepo.findAll();
	}

	@Override
	public Categoria add(Categoria c) {
		return categoriaRepo.save(c);
	}

	@Override
	public Categoria update(Categoria c) {
		return categoriaRepo.save(c);
	}

	@Override
	public void delete(int id) {
		categoriaRepo.deleteById(id);	
		
	}

}
