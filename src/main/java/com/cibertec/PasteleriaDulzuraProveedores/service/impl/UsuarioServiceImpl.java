package com.cibertec.PasteleriaDulzuraProveedores.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.PasteleriaDulzuraProveedores.model.Usuario;
import com.cibertec.PasteleriaDulzuraProveedores.repo.UsuarioRepo;
import com.cibertec.PasteleriaDulzuraProveedores.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	UsuarioRepo userRepo;

	@Override
	public List<Usuario> listUsuario() {
		return userRepo.findAll();
	}

	@Override
	public Usuario add(Usuario u) {
		return userRepo.save(u);
	}

	@Override
	public Usuario getUserByID(int codigo) {
		return userRepo.findById(codigo).orElse(new Usuario());
	}

	@Override
	public Usuario update(Usuario u) {
		return userRepo.save(u);
	}

	@Override
	public void delete(int id) {
		userRepo.deleteById(id);
	}

}
