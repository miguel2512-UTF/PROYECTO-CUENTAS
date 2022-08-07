package com.cuentas.proyectocuentas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cuentas.proyectocuentas.service.IPrestamoService;
import com.cuentas.proyectocuentas.model.Prestamo;

@Controller
@RequestMapping("/prestamo")
public class PrestamoController {

    @Autowired
    private IPrestamoService prestamoI;

    @GetMapping("/listar")
    public String listar(Model m){
        m.addAttribute("prestamos", prestamoI.findAll());
        Prestamo prestamo=new Prestamo();
        m.addAttribute("prestamo", prestamo);
        return "views/prestamo/prestamos";
    }

    @PostMapping("/add")
    public String add(@Valid Prestamo prestamo, BindingResult res, Model m){
        m.addAttribute("prestamos", prestamoI.findAll());

        if (res.hasErrors()) {
            return "views/prestamo/prestamos";
        }

        prestamoI.save(prestamo);
        return "redirect:/prestamo/listar";
    }

    @GetMapping("/delete/{idPrestamo}")
    public String delete(@PathVariable Integer idPrestamo){
        if (idPrestamo > 0) {
            prestamoI.delete(idPrestamo);
        }
        return "redirect:/prestamo/listar";
    }




    
}
