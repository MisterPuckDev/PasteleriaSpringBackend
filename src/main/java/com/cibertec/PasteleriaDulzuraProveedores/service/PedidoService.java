package com.cibertec.PasteleriaDulzuraProveedores.service;

import java.util.List;

import com.cibertec.PasteleriaDulzuraProveedores.model.Pedido;

public interface PedidoService {
	
	public List<Pedido> listPedido();
	public Pedido add(Pedido d);

}
