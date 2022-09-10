package com.cuentas.proyectocuentas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuentas.proyectocuentas.model.IPrestamoAbono;
import com.cuentas.proyectocuentas.model.PrestamoAbono;

@Service
public class PrestamoAbonoService implements IPrestamoAbonoService{
 
    @Autowired
    private IPrestamoAbono prestamoabonoI;


    @Override
    public List<PrestamoAbono> findAll() {
        return (List<PrestamoAbono>) prestamoabonoI.findAll();
    } 

    @Override
    public void save(PrestamoAbono prestamoabono) {
        prestamoabonoI.save(prestamoabono);
    }

    @Override
    public PrestamoAbono findOne(Integer idPrestamoAbono) {
        return prestamoabonoI.findById(idPrestamoAbono).orElse(null);
    }

    @Override
    public void delete(Integer idPrestamoAbono) {
        prestamoabonoI.deleteById(idPrestamoAbono);        
    }
    
}
