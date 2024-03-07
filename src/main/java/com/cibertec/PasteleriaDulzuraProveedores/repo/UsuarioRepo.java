package com.cibertec.PasteleriaDulzuraProveedores.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.PasteleriaDulzuraProveedores.model.Usuario;


import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer>  {

    Optional<Usuario> findByUsername(String username);

}
