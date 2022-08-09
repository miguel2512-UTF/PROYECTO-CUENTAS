package com.cuentas.proyectocuentas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuentas.proyectocuentas.model.IPrestamo;
import com.cuentas.proyectocuentas.model.Prestamo;

@Service
public class PrestamoService implements IPrestamoService {
    
    @Autowired
    private IPrestamo prestamoI;
    
    @Override
    public List<Prestamo> findAll() {
        return (List<Prestamo>) prestamoI.findAll();
    }

    @Override
    public void save(Prestamo prestamo) {
        prestamoI.save(prestamo);        
    }

    @Override
    public Prestamo findOne(Integer idPrestamo) {
        return prestamoI.findById(idPrestamo).orElse(null);

    }

    @Override
    public void delete(Integer idPrestamo) {
        prestamoI.deleteById(idPrestamo);
        
    }



}
