package com.cibertec.PasteleriaDulzuraProveedores.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedidoId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Lima")
	private LocalDateTime fechaRegistro;
    
    private double subTotal;
    private int igv;
    private double total;
    
    @ManyToOne
	@JoinColumn(name="proveedor_id")	
	private Proveedor proveedor;
    
    @ManyToOne
	@JoinColumn(name="usuario_id")	
	private Usuario usuario;
    
    @PrePersist
	protected void onCreate() {
    	fechaRegistro = LocalDateTime.now();
	}

}
