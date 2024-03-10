package com.cibertec.PasteleriaDulzuraProveedores.repo;

import com.cibertec.PasteleriaDulzuraProveedores.model.PedidoHasProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoHasProductoRepository extends JpaRepository<PedidoHasProducto, Integer> {

}
