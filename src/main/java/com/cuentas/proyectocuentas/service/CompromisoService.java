package com.cuentas.proyectocuentas.service;

import java.util.List;
import java.util.Optional;

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
     private boolean checkFacturaAvailable(Compromiso compromiso) throws Exception{
        Optional<Compromiso> userFound=compromisod.findBynumeroFac(compromiso.getNumeroFac());
        if (userFound.isPresent()) {
            throw new Exception("numero de factura no disponible");	
        }
        return true;
    }
    @Override
    public Compromiso createCompromiso(Compromiso compromiso) throws Exception {
        if (checkFacturaAvailable(compromiso)) {
            compromiso = compromisod.save(compromiso);
        }
        return compromiso;
    }

   
    
}
