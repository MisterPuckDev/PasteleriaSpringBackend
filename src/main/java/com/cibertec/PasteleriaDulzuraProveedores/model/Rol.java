package com.cibertec.PasteleriaDulzuraProveedores.model;

import com.cibertec.PasteleriaDulzuraProveedores.auth.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "rol", uniqueConstraints = {@UniqueConstraint(columnNames = {"codigo"})})
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rolId;
	private Role codigo;
	private String nombre;
	private int estado;

}
