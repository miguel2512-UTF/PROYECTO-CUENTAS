package com.cuentas.proyectocuentas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cuentas.proyectocuentas.model.ITipoCompromiso;
import com.cuentas.proyectocuentas.model.TipoCompromiso;

public class TipoCompromisoService  implements ITipoCompromisoService {




        @Autowired
        private ITipoCompromiso tipocompromisod;
    
        @Override
        public List<TipoCompromiso> findAll() {
            
            return (List<TipoCompromiso>) tipocompromisod.findAll();
        }
    
       @Override
       public void save(TipoCompromiso tipocompromiso){
        tipocompromisod.save(tipocompromiso);
       }
    
        @Override
        public TipoCompromiso findOne(Integer id) {
            
            return tipocompromisod.findById(id).orElse(null);
        }
    
        @Override
        public void delete(Integer id) {
            tipocompromisod.deleteById(id);
            
        }
     