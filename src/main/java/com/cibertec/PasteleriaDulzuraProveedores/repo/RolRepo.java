package com.cibertec.PasteleriaDulzuraProveedores.repo;

import com.cibertec.PasteleriaDulzuraProveedores.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.PasteleriaDulzuraProveedores.model.Rol;

public interface RolRepo extends JpaRepository<Rol, Integer>  {

    Rol findByCodigo(Role codigo);

}
