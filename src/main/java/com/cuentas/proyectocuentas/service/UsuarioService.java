package com.cuentas.proyectocuentas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuentas.proyectocuentas.model.IUsuario;
import com.cuentas.proyectocuentas.model.Usuario;

@Service
public class UsuarioService implements IUsuarioService{
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

    private boolean checkUsernameAvailable(Usuario usuario) throws Exception{
        Optional<Usuario> userFound=usuarioI.findByNombresUsuario(usuario.getNombresUsuario());
        if (userFound.isPresent()) {
            throw new Exception("Username no disponible");	
        }
        return true;
    }

    private boolean checkEmailAvailable(Usuario usuario) throws Exception{
        Optional<Usuario> emailFound=usuarioI.findByCorreoUsuario(usuario.getCorreoUsuario());
        if (emailFound.isPresent()) {
            throw new Exception("Email no disponible");
        }
        return true;
    }
    
    @Override
    public Usuario createUser(Usuario usuario) throws Exception {
        if (checkUsernameAvailable(usuario) && checkEmailAvailable(usuario)) {
            usuario = usuarioI.save(usuario);
        }
        return usuario;
    }

    @Override
    public Usuario login(Usuario usuario) {
        Optional<Usuario> usu=usuarioI.findByNombresUsuario(usuario.getNombresUsuario());
        Usuario usu1=new Usuario();

        if (usu.isPresent()) {
            usu1=usu.get();
        }
        
        if (usuario.getNombresUsuario().equalsIgnoreCase(usu1.getNombresUsuario()) && usuario.getContrasenaUsuario().equalsIgnoreCase(usu1.getContrasenaUsuario())) {
            System.out.println("Se encontro el user");
            return usu1;
        }else{
            return null; 
        }
    }
}
