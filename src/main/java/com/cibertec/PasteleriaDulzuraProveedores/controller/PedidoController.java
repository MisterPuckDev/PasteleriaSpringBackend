package com.cibertec.PasteleriaDulzuraProveedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.PasteleriaDulzuraProveedores.model.Pedido;
import com.cibertec.PasteleriaDulzuraProveedores.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> list(){
		return  new ResponseEntity<>(pedidoService.listPedido(), HttpStatus.OK)  ;
	}
	
	@PostMapping
	public ResponseEntity<Pedido> add(@RequestBody Pedido p) {
		return new ResponseEntity<>(pedidoService.add(p), HttpStatus.CREATED);
	}	
	
}