package com.cibertec.PasteleriaDulzuraProveedores.model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class PedidoHasProductoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private int pedidoid;
	private int productoId;

}
