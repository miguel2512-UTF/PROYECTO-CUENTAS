package com.cuentas.proyectocuentas.service;

import java.util.List;

import com.cuentas.proyectocuentas.model.PagoPrestamo;

public interface IPagoPrestamoService {
   
    public List<PagoPrestamo> findAll();
    public void save (PagoPrestamo pagoprestamo);
    public PagoPrestamo findOne(Integer idPagoPrestamo);
    public void delete(Integer idPagoPrestamo); 
}
