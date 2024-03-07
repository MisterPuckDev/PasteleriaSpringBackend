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

import com.cibertec.PasteleriaDulzuraProveedores.model.Producto;
import com.cibertec.PasteleriaDulzuraProveedores.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	ProductoService productoService;

	@GetMapping
	public ResponseEntity<List<Producto>> list(){
		return  new ResponseEntity<>(productoService.listProducto(), HttpStatus.OK)  ;
	}
	
	@PostMapping
	public ResponseEntity<Producto> add(@RequestBody Producto p) {
		return new ResponseEntity<>(productoService.add(p), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Producto> update(@RequestBody Producto p) {
		return new ResponseEntity<>(productoService.update(p), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		productoService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}