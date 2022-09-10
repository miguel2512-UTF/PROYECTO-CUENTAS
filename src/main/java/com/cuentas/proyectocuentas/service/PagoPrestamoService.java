package com.cuentas.proyectocuentas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuentas.proyectocuentas.model.IPagoPrestamo;
import com.cuentas.proyectocuentas.model.PagoPrestamo;

@Service
public class PagoPrestamoService implements IPagoPrestamoService  {

    @Autowired
    private IPagoPrestamo pagoprestamoI;

    @Override
    public List<PagoPrestamo> findAll() {
        return (List<PagoPrestamo>) pagoprestamoI.findAll();
    }
 
    @Override
    public void save(PagoPrestamo pagoprestamo) {
        pagoprestamoI.save(pagoprestamo);
    }

    @Override
    public PagoPrestamo findOne(Integer idPagoPrestamo) {
        return pagoprestamoI.findById(idPagoPrestamo).orElse(null);
    }

    @Override
    public void delete(Integer idPagoPrestamo) {
        pagoprestamoI.deleteById(idPagoPrestamo);        
        
    }
    
}
