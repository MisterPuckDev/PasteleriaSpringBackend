package com.cibertec.PasteleriaDulzuraProveedores.auth;

import com.cibertec.PasteleriaDulzuraProveedores.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    String username;
    String nombre;
    String apellido;
    String dni;
    String password;
    String correo;
    Rol rol;
}
