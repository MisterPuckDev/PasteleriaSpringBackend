package com.cibertec.PasteleriaDulzuraProveedores.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.PasteleriaDulzuraProveedores.model.PedidoDetalle;
import com.cibertec.PasteleriaDulzuraProveedores.repo.PedidoDetalleRepo;
import com.cibertec.PasteleriaDulzuraProveedores.service.PedidoDetalleService;

@Service
public class PedidoDetalleServiceImpl implements PedidoDetalleService{
	
	@Autowired
	PedidoDetalleRepo pedidoDetalleRepo;

	@Override
	public List<PedidoDetalle> findPedidoDetalleByPedidoId(int pedidoId) {
		return pedidoDetalleRepo.findByPedidoId(pedidoId);
	}

}
