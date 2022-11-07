package com.cuentas.proyectocuentas.service;

import java.util.List;

import com.cuentas.proyectocuentas.model.Usuario;

public interface IUsuarioService{
    public List<Usuario> findAll();
    public void save(Usuario usuario);
    public Usuario findOne(Integer idUsuario);
    public void delete(Integer idUsuario);
    public Usuario createUser(Usuario usuario) throws Exception;
    // public Usuario login(Usuario usuario);
}
