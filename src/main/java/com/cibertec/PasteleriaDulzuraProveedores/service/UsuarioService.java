package com.cibertec.PasteleriaDulzuraProveedores.service;

import java.util.List;

import com.cibertec.PasteleriaDulzuraProveedores.model.Usuario;

public interface UsuarioService {
	
	public List<Usuario> listUsuario();
	public Usuario add(Usuario u);
	public Usuario update(Usuario u);
	public void delete(int id);
	public Usuario getUserByID(int codigo);

}
