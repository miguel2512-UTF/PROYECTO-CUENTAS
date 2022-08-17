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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


import com.cuentas.proyectocuentas.model.PrestamoAbono;
import com.cuentas.proyectocuentas.service.IPrestamoAbonoService;

@Controller
@SessionAttributes("prestamoabono")
@RequestMapping("/prestamoabono")
public class PrestamoAbonoController {

    @Autowired
    private IPrestamoAbonoService prestamoabonoI;


    //LISTAR
    @GetMapping("/listar")
    public String listar(Model m){
        m.addAttribute("prestamoabonos", prestamoabonoI.findAll());
        PrestamoAbono prestamoabono=new PrestamoAbono();
        m.addAttribute("prestamoabono", prestamoabono);
        return "views/prestamoabono/prestamoabono";
    }

    //AGREGAR
    @PostMapping("/add")
    public String add(@Valid PrestamoAbono prestamoabono, BindingResult res, Model m, SessionStatus status){
        if (res.hasErrors()) {
            return "views/prestamoabono/registrar";
        }
        prestamoabonoI.save(prestamoabono);
        status.setComplete();
        return "redirect:listar";
    }

    @GetMapping("/formulario")
    public String formulario(Model m){
        PrestamoAbono prestamoabono = new PrestamoAbono();
        m.addAttribute("prestamoabono", prestamoabono);
        return "views/prestamoabono/registrar";
    }


    //EDITAR
    @GetMapping(path = {"/editar/{idPrestamoAbono}", "/Editar/{idPrestamoAbono}"})
    public String editar (@PathVariable Integer idPrestamoAbono, Model m){
        PrestamoAbono prestamoabono=null;
        if(idPrestamoAbono > 0){
            prestamoabono=prestamoabonoI.findOne(idPrestamoAbono);
        }
        m.addAttribute("prestamoabono", prestamoabono);
        return "views/prestamoabono/editar"; 
    }
 

    //ELIMINAR
    @GetMapping("/delete/{idPrestamoAbono}")
    public String delete(@PathVariable Integer idPrestamoAbono){
        if (idPrestamoAbono > 0) {
            prestamoabonoI.delete(idPrestamoAbono);
        }
        return "redirect:../listar";
    }

    
}
