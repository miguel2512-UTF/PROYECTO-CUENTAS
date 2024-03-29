package com.cuentas.proyectocuentas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import com.cuentas.proyectocuentas.model.IUsuario;
import com.cuentas.proyectocuentas.model.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private IUsuario usuarioI;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioI.findByCorreoUsuario(correo);
        UserBuilder builder = null;

        if (usuario != null) {
            builder = User.withUsername(correo);
            builder.disabled(false);
            builder.password(usuario.getContrasenaUsuario());
            if (usuario.getEstadoUsuario().equalsIgnoreCase("activo")) {
                builder.authorities(getAuthorities(usuario));
            }else{
                builder.authorities("INACTIVO");
            }
        } else {
            throw new UsernameNotFoundException("usuario no encontrado");
        }

        return builder.build();
    }

    private GrantedAuthority getAuthorities(Usuario usuario){
        GrantedAuthority authorities = new SimpleGrantedAuthority(usuario.getTipoUsuario().toUpperCase());

        return authorities;
    }
    
}
