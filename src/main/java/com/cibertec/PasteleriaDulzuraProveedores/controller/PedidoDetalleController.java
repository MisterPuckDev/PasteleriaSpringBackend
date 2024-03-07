package com.cibertec.PasteleriaDulzuraProveedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.PasteleriaDulzuraProveedores.model.PedidoDetalle;
import com.cibertec.PasteleriaDulzuraProveedores.service.PedidoDetalleService;

@RestController
@RequestMapping("/pedido_detalle")
public class PedidoDetalleController {
	
	@Autowired
	PedidoDetalleService pedidoDetalleService;
	
	@GetMapping("/{pedidoId}")
    public ResponseEntity<List<PedidoDetalle>> getPedidoDetalleByPedidoId(@PathVariable("pedidoId") int pedidoId) {
        List<PedidoDetalle> pedidoDetalle = pedidoDetalleService.findPedidoDetalleByPedidoId(pedidoId);
        if(pedidoDetalle.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pedidoDetalle, HttpStatus.OK);
    }
}