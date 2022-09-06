package com.cuentas.proyectocuentas.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ICompromiso extends CrudRepository<Compromiso,Integer> {
    public Optional<Compromiso> findBynumeroFac(String numeroFac);
}
