package com.cuentas.proyectocuentas.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IUsuario extends CrudRepository<Usuario, Integer>{

    public Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    public Optional<Usuario> findByCorreoUsuario(String correoUsuario);

}
