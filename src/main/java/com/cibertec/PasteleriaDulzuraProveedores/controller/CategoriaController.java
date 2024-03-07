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

import com.cibertec.PasteleriaDulzuraProveedores.model.Categoria;
import com.cibertec.PasteleriaDulzuraProveedores.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> list(){
		return  new ResponseEntity<>(categoriaService.listCategoria(), HttpStatus.OK)  ;
	}
	
	@PostMapping
	public ResponseEntity<Categoria> add(@RequestBody Categoria c) {
		return new ResponseEntity<>(categoriaService.add(c), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Categoria> update(@RequestBody Categoria c) {
		return new ResponseEntity<>(categoriaService.update(c), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		categoriaService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
