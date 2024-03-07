package com.cibertec.PasteleriaDulzuraProveedores.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.PasteleriaDulzuraProveedores.model.PedidoDetalle;

public interface PedidoDetalleRepo extends JpaRepository<PedidoDetalle, Integer>  {
	
	// Define un método para buscar los detalles de un pedido específico por el ID del pedido
    @Query("SELECT od FROM PedidoDetalle od WHERE od.pedido.pedidoId = :pedidoId")
    List<PedidoDetalle> findByPedidoId(@Param("pedidoId") int pedidoId);

}
