package com.cuentas.proyectocuentas.service;
import java.util.List;

import com.cuentas.proyectocuentas.model.Prestamo;

public interface IPrestamoService {
    public List<Prestamo> findAll();
    public void save (Prestamo prestamo);
    public Prestamo findOne(Integer idPrestamo);
    public void delete(Integer idPrestamo);
}
