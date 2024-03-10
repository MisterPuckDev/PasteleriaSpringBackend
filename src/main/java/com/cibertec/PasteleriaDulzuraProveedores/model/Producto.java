package com.cibertec.PasteleriaDulzuraProveedores.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productoId;
	private String nombre;
	private int stock;
	private double precio;	

	@ManyToOne(optional = false)
	@JoinColumn(name = "categoriaId", nullable = false)
	private Categoria categoria;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "proveedorId", nullable = false)
	private Proveedor proveedor;

}
