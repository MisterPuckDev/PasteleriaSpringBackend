package com.cibertec.PasteleriaDulzuraProveedores.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.PasteleriaDulzuraProveedores.model.Pedido;
import com.cibertec.PasteleriaDulzuraProveedores.service.PedidoService;

@RestController
@RequestMapping("/url/pedido")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> list(){
		return  new ResponseEntity<>(pedidoService.listPedido(), HttpStatus.OK)  ;
	}

	@ResponseBody
	@PostMapping("/registraProducto")
	public HashMap<String, Object> registraBoleta(@RequestBody Pedido objPedido){
		HashMap<String, Object> mapSalida = new HashMap<String, Object>();
		Pedido objPedidoSalida = pedidoService.add(objPedido);
		if (objPedidoSalida != null) {
			mapSalida.put("mensaje", "Se registró el pedido");
			mapSalida.put("data", objPedidoSalida);
		}else {
			mapSalida.put("mensaje", "Error en el registro del pedido");
		}
		return mapSalida;
	}
	
}