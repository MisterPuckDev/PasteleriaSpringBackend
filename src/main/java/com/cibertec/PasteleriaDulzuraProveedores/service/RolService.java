package com.cibertec.PasteleriaDulzuraProveedores.service;

import java.util.List;

import com.cibertec.PasteleriaDulzuraProveedores.model.Rol;

public interface RolService {
	
	public List<Rol> listRol();
	public Rol add(Rol r);
	public Rol update(Rol r);
	public void delete(int id);


}
