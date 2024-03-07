package com.cibertec.PasteleriaDulzuraProveedores.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.PasteleriaDulzuraProveedores.model.Pedido;
import com.cibertec.PasteleriaDulzuraProveedores.repo.PedidoRepo;
import com.cibertec.PasteleriaDulzuraProveedores.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	@Autowired
	PedidoRepo pedidoRepo;

	@Override
	public List<Pedido> listPedido() {
		return pedidoRepo.findAll();
	}

	@Override
	public Pedido add(Pedido p) {
		return pedidoRepo.save(p);
	}

}
