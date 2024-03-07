package com.cibertec.PasteleriaDulzuraProveedores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "suministro")
public class Suministro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int suministroId;
	private String descripcion;

}
