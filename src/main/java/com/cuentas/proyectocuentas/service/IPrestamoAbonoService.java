package com.cuentas.proyectocuentas.service;

import java.util.List;

import com.cuentas.proyectocuentas.model.PrestamoAbono;

public interface IPrestamoAbonoService {

    public List<PrestamoAbono> findAll();
    public void save (PrestamoAbono prestamoabono);
    public PrestamoAbono findOne(Integer idPrestamoAbono);
    public void delete(Integer idPrestamoAbono);
    
}
