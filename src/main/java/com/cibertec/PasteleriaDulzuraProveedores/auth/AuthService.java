package com.cibertec.PasteleriaDulzuraProveedores.auth;

import com.cibertec.PasteleriaDulzuraProveedores.jwt.JwtService;
import com.cibertec.PasteleriaDulzuraProveedores.model.Rol;
import com.cibertec.PasteleriaDulzuraProveedores.model.Usuario;
import com.cibertec.PasteleriaDulzuraProveedores.repo.RolRepo;
import com.cibertec.PasteleriaDulzuraProveedores.repo.UsuarioRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepo userRepository;
    private final RolRepo rolRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();

    }

    public AuthResponse register(UserRequest request) {

        Rol rol = rolRepository.findByCodigo(Role.USER);

        if (rol == null) {
            rol = new Rol();
            rol.setCodigo(Role.USER);
            rol.setNombre("Usuario");
            rol.setEstado(1);
            rolRepository.save(rol);
        }

        Usuario user = Usuario.builder()
                .username(request.getUsername())
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .dni(request.getDni())
                .password(passwordEncoder.encode( request.getPassword()))
                .correo(request.getCorreo())
                .rol(rol)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }

}
