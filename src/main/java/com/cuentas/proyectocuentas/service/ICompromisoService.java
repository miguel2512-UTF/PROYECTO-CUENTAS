package com.cuentas.proyectocuentas.service;

import java.util.List;



import com.cuentas.proyectocuentas.model.Compromiso;

public interface ICompromisoService {
    public List<Compromiso> findAll();
    public void save (Compromiso compromiso);
    public Compromiso findOne(Integer idCom);
    public void delete(Integer idCom);
   public Compromiso createCompromiso(Compromiso compromiso) throws Exception;
        
   
    
}
