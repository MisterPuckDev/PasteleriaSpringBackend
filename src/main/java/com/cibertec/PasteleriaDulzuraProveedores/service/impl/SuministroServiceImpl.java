package com.cibertec.PasteleriaDulzuraProveedores.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.PasteleriaDulzuraProveedores.model.Suministro;
import com.cibertec.PasteleriaDulzuraProveedores.repo.SuministroRepo;
import com.cibertec.PasteleriaDulzuraProveedores.service.SuministroService;

@Service
public class SuministroServiceImpl implements SuministroService{
	
	@Autowired
	private SuministroRepo suministroRepo;
	
	@Override
	public List<Suministro> listaSuministro() {
		return suministroRepo.findAll();
	}
	
}
