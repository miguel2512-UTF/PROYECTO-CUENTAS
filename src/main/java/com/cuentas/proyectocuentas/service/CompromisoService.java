package com.cuentas.proyectocuentas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuentas.proyectocuentas.model.Compromiso;
import com.cuentas.proyectocuentas.model.ICompromiso;


@Service
public class CompromisoService implements ICompromisoService{
    @Autowired
    private ICompromiso compromisoI;

    @Override
    public List<Compromiso> findAll() {
        return (List<Compromiso>) compromisoI.findAll();
    }

    @Override
    public void save(Compromiso compromiso) {
        compromisoI.save(compromiso);
    }

    @Override
    public Compromiso findOne(Integer idCompromiso) {
        return compromisoI.findById(idCompromiso).orElse(null);
    }

    @Override
    public void delete(Integer idCompromiso) {
        compromisoI.deleteById(idCompromiso);
    }
}

