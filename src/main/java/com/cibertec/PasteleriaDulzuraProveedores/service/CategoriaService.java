package com.cibertec.PasteleriaDulzuraProveedores.service;

import java.util.List;

import com.cibertec.PasteleriaDulzuraProveedores.model.Categoria;

public interface CategoriaService {
	
	public List<Categoria> listCategoria();
	public Categoria add(Categoria c);
	public Categoria update(Categoria c);
	public void delete(int id);

}
