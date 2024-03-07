package com.cibertec.PasteleriaDulzuraProveedores.service;

import java.util.List;

import com.cibertec.PasteleriaDulzuraProveedores.model.PedidoDetalle;

public interface PedidoDetalleService {
	
	public List<PedidoDetalle> findPedidoDetalleByPedidoId(int pedidoId);

}
