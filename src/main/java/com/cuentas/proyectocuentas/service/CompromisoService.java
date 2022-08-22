package com.cuentas.proyectocuentas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuentas.proyectocuentas.model.Compromiso;
import com.cuentas.proyectocuentas.model.ICompromiso;

@Service
public class CompromisoService implements ICompromisoService {
    @Autowired
    private ICompromiso compromisod;

    @Override
    public List<Compromiso> findAll() {
        
        return (List<Compromiso>) compromisod.findAll();
    }

    @Override
    public void save(Compromiso compromiso) {
        compromisod.save(compromiso);        
    }

    @Override
    public Compromiso findOne(Integer idCom) {
        
        return compromisod.findById(idCom).orElse(null);
    }

    @Override
    public void delete(Integer idCom) {
       compromisod.deleteById(idCom);
        
    }
    
}
