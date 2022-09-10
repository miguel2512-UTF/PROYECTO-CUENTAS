package com.cuentas.proyectocuentas.service;

import java.util.List;

import com.cuentas.proyectocuentas.model.TipoCompromiso;

public interface ITipoCompromisoService {
    
    public List<TipoCompromiso> findAll();
    public void save (TipoCompromiso tipocompromiso);
    public TipoCompromiso findOne(Integer id);
    public void delete(Integer id);
    public TipoCompromiso createTipoCompromiso(TipoCompromiso tipocompromiso) throws Exception;
        
}
