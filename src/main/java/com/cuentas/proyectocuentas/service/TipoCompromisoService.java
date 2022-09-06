package com.cuentas.proyectocuentas.service;

import java.util.List;
import java.util.Optional;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuentas.proyectocuentas.model.ITipoCompromiso;
import com.cuentas.proyectocuentas.model.TipoCompromiso;

@Service
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
        private boolean checkNombreAvailable(TipoCompromiso tipocompromiso) throws Exception{
            Optional<TipoCompromiso> userFound=tipocompromisod.findByNombre(tipocompromiso.getNombre());
            if (userFound.isPresent()) {
                throw new Exception("NOMBRE no disponible");	
            }
            return true;
        }
        @Override
        public TipoCompromiso createTipoCompromiso(TipoCompromiso tipocompromiso) throws Exception {
            if (checkNombreAvailable(tipocompromiso)) {
                tipocompromiso = tipocompromisod.save(tipocompromiso);
            }
            return tipocompromiso;
        }
   
} 