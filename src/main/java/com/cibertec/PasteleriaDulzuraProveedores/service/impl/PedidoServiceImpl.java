package com.cibertec.PasteleriaDulzuraProveedores.service.impl;

import java.util.List;

import com.cibertec.PasteleriaDulzuraProveedores.model.PedidoHasProducto;
import com.cibertec.PasteleriaDulzuraProveedores.repo.PedidoHasProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.PasteleriaDulzuraProveedores.model.Pedido;
import com.cibertec.PasteleriaDulzuraProveedores.repo.PedidoRepo;
import com.cibertec.PasteleriaDulzuraProveedores.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	@Autowired
	PedidoRepo pedidoRepo;

	@Autowired
	private PedidoHasProductoRepository detalleRepository;

	@Override
	public List<Pedido> listPedido() {
		return pedidoRepo.findAll();
	}

	@Override
	public Pedido add(Pedido p) {
		Pedido cabecera = pedidoRepo.save(p);
		for (PedidoHasProducto d : cabecera.getDetallesPedido()) {
			d.getPedidoHasProductoPK().setPedidoid(cabecera.getPedidoId());
			detalleRepository.save(d);
		}
		return cabecera;
	}

}
