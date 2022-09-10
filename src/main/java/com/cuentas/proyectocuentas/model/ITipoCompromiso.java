package com.cuentas.proyectocuentas.model;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ITipoCompromiso extends CrudRepository<TipoCompromiso,Integer>{
    public Optional<TipoCompromiso> findByNombre(String nombre);
}
