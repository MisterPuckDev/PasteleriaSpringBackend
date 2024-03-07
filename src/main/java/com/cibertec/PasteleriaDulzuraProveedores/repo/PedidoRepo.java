package com.cibertec.PasteleriaDulzuraProveedores.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.PasteleriaDulzuraProveedores.model.Pedido;

public interface PedidoRepo extends JpaRepository<Pedido, Integer>  {

}
