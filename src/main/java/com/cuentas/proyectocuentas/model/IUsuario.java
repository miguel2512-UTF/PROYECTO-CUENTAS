package com.cuentas.proyectocuentas.model;

import org.springframework.data.repository.CrudRepository;

public interface IUsuario extends CrudRepository<Usuario, Integer>{

    public Usuario findByNombresUsuario(String nombresUsuario);

    public Usuario findByCorreoUsuario(String correoUsuario);

}
