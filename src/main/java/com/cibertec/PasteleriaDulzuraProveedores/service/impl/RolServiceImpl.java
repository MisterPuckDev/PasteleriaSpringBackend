package com.cibertec.PasteleriaDulzuraProveedores.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.PasteleriaDulzuraProveedores.model.Rol;
import com.cibertec.PasteleriaDulzuraProveedores.repo.RolRepo;
import com.cibertec.PasteleriaDulzuraProveedores.service.RolService;

@Service
public class RolServiceImpl implements RolService{
	
	@Autowired
	RolRepo rolRepo;

	@Override
	public List<Rol> listRol() {
		return rolRepo.findAll();
	}

	@Override
	public Rol add(Rol r) {
		return rolRepo.save(r);
	}

	@Override
	public Rol update(Rol r) {
		return rolRepo.save(r);
	}

	@Override
	public void delete(int id) {
		rolRepo.deleteById(id);		
	}

}
