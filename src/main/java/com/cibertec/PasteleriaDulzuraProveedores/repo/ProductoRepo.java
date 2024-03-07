package com.cibertec.PasteleriaDulzuraProveedores.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.PasteleriaDulzuraProveedores.model.Producto;

public interface ProductoRepo extends JpaRepository<Producto, Integer>  {

}
