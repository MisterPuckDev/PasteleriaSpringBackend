package com.cibertec.PasteleriaDulzuraProveedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.PasteleriaDulzuraProveedores.model.Rol;
import com.cibertec.PasteleriaDulzuraProveedores.service.RolService;

@RestController
@RequestMapping("/rol")
public class RolController {
	
	@Autowired
	RolService rolService;

	@GetMapping
	public ResponseEntity<List<Rol>> list(){
		return  new ResponseEntity<>(rolService.listRol(), HttpStatus.OK)  ;
	}
	
	@PostMapping
	public ResponseEntity<Rol> add(@RequestBody Rol r) {
		return new ResponseEntity<>(rolService.add(r), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Rol> update(@RequestBody Rol r) {
		return new ResponseEntity<>(rolService.update(r), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		rolService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}