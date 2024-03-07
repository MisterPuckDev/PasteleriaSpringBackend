package com.cibertec.PasteleriaDulzuraProveedores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pedido_detalle")
public class PedidoDetalle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pedidoDetalleId;
	
	@ManyToOne
	@JoinColumn(name="pedido_id")	
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="producto_id")	
	private Producto producto;
	
	private int cantidad;	
    private double precioUnitario;    
    private double subTotal;

}
