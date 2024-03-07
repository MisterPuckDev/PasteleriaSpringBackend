package com.cibertec.PasteleriaDulzuraProveedores.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cibertec.PasteleriaDulzuraProveedores.model.Usuario;
import com.cibertec.PasteleriaDulzuraProveedores.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;

	@GetMapping(value = "{id}")
	public ResponseEntity<Usuario> getUser(@PathVariable int id)
	{
		Usuario userDTO = usuarioService.getUserByID(id);
		if (userDTO==null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userDTO);
	}

	@PutMapping()
	public ResponseEntity<Usuario> updateUser(@RequestBody Usuario usuario)
	{
		return ResponseEntity.ok(usuarioService.update(usuario));
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> list(){
		return  new ResponseEntity<>(usuarioService.listUsuario(), HttpStatus.OK)  ;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> add(@RequestBody Usuario u) {
		return new ResponseEntity<>(usuarioService.add(u), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		usuarioService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}