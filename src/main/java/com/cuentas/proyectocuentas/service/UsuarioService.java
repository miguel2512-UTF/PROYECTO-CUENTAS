package com.cuentas.proyectocuentas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cuentas.proyectocuentas.model.IUsuario;
import com.cuentas.proyectocuentas.model.Usuario;

@Service
public class UsuarioService implements IUsuarioService{
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IUsuario usuarioI;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioI.findAll();
    }

    @Override
    public void save(Usuario usuario) {
        usuarioI.save(usuario);
    }

    @Override
    public Usuario findOne(Integer idUsuario) {
        return usuarioI.findById(idUsuario).orElse(null);
    }

    @Override
    public void delete(Integer idUsuario) {
        usuarioI.deleteById(idUsuario);
    }

    public boolean checkEmailAvailable(Usuario usuario) throws Exception{
        Usuario emailFound=usuarioI.findByCorreoUsuario(usuario.getCorreoUsuario());
        if (emailFound!=null) {
            throw new Exception("Email no disponible");
        }
        return true;
    }
    
    @Override
    public Usuario createUser(Usuario usuario) throws Exception {
        if (checkEmailAvailable(usuario)) {
            usuario.setContrasenaUsuario(passwordEncoder.encode(usuario.getContrasenaUsuario()));
            usuario = usuarioI.save(usuario);
        }
        return usuario;
    }

    @Override
    public Usuario findOneByEmail(String email) {
        Usuario usuario = usuarioI.findByCorreoUsuario(email);
        return usuario;
    }

    // @Override
    // public Usuario login(Usuario usuario) {
    //     Optional<Usuario> usu=usuarioI.findByNombresUsuario(usuario.getNombresUsuario());
    //     Usuario usu1=new Usuario();

    //     if (usu.isPresent()) {
    //         usu1=usu.get();
    //     }
        
    //     if (usuario.getNombresUsuario().equalsIgnoreCase(usu1.getNombresUsuario()) && usuario.getContrasenaUsuario().equalsIgnoreCase(usu1.getContrasenaUsuario())) {
    //         System.out.println("Se encontro el user");
    //         return usu1;
    //     }else{
    //         return null; 
    //     }
    // }
    
}
