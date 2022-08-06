package com.cuentas.proyectocuentas.service;

import java.util.List;

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
}
