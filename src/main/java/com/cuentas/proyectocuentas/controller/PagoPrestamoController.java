package com.cuentas.proyectocuentas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cuentas.proyectocuentas.model.PagoPrestamo;
import com.cuentas.proyectocuentas.service.IPagoPrestamoService;
import com.cuentas.proyectocuentas.service.IPrestamoService;

@Controller
@SessionAttributes("pagoprestamo")
@RequestMapping("/pagoprestamo")
public class PagoPrestamoController {

    @Autowired
    private IPagoPrestamoService pagoprestamoI;

    @Autowired
    private IPrestamoService prestamoI;

    //LISTAR
    @GetMapping("/listar")
    public String listar(Model m){
        m.addAttribute("pagoprestamos", pagoprestamoI.findAll());
        PagoPrestamo pagoprestamo=new PagoPrestamo();
        m.addAttribute("pagoprestamo", pagoprestamo);
        m.addAttribute("prestamo", prestamoI.findAll());
        return "views/pagoprestamo/pagoprestamo";
    }

    
    //AGREGAR
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("pagoprestamo") PagoPrestamo pagoprestamo, BindingResult res, Model m, SessionStatus status){
        m.addAttribute("pagoprestamos", pagoprestamoI.findAll());
        m.addAttribute("prestamo", prestamoI.findAll());
        
        if (res.hasErrors()) {
            return "views/pagoprestamo/pagoprestamo";
        }
        pagoprestamoI.save(pagoprestamo);
        status.setComplete();
        return "redirect:listar";
    }
    
    @GetMapping("/formulario")
    public String formulario(Model m){
        PagoPrestamo pagoprestamo = new PagoPrestamo();
        m.addAttribute("pagoprestamo", pagoprestamo);
        m.addAttribute("prestamo", prestamoI.findAll());
        return "views/pagoprestamo/registrar";
    }

    //EDITAR
    @GetMapping(path = {"/editar/{idPagoPrestamo}", "/Editar/{idPagoPrestamo}"})
    public String editar (@PathVariable Integer idPagoPrestamo, Model m){
        PagoPrestamo pagoprestamo=null;
        if(idPagoPrestamo > 0){
            pagoprestamo=pagoprestamoI.findOne(idPagoPrestamo);
        }
        m.addAttribute("pagoprestamo", pagoprestamo);
        m.addAttribute("prestamo", prestamoI.findAll());
        return "views/pagoprestamo/editar"; 
     }
 

    //ELIMINAR
    @GetMapping("/delete/{idPagoPrestamo}")
    public String delete(@PathVariable Integer idPagoPrestamo){
        if (idPagoPrestamo > 0) {
            pagoprestamoI.delete(idPagoPrestamo);
        }
        return "redirect:../listar";
    }
}
 
