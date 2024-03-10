package com.cibertec.PasteleriaDulzuraProveedores.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pedido_has_producto")
public class PedidoHasProducto {

	@EmbeddedId
	private PedidoHasProductoPK pedidoHasProductoPK;
	private double precio;
	private int cantidad;

	@ManyToOne
	@JoinColumn(name = "pedidoid", nullable = false, insertable = false, updatable = false)
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "productoId", nullable = false, insertable = false, updatable = false)
	private Producto producto;

}
