package com.cibertec.PasteleriaDulzuraProveedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.PasteleriaDulzuraProveedores.model.Suministro;
import com.cibertec.PasteleriaDulzuraProveedores.service.SuministroService;
import com.cibertec.PasteleriaDulzuraProveedores.util.AppSettings;

@RestController
@RequestMapping("/url/util")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class UtilController {

	@Autowired
	private SuministroService suministroService;	
	
	@GetMapping("/listaSuministro")
	@ResponseBody
	public List<Suministro> listaSuministro() {
		return suministroService.listaSuministro();
	}
	
}
