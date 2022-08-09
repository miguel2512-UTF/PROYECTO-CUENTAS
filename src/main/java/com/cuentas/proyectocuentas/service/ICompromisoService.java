package com.cuentas.proyectocuentas.service;

import java.util.List;

import com.cuentas.proyectocuentas.model.Compromiso;



public interface ICompromisoService {
    public List<Compromiso> findAll();
    public void save(Compromiso compromiso);
    public Compromiso findOne(Integer idCompromiso);
    public void delete(Integer idCompromiso);
}

