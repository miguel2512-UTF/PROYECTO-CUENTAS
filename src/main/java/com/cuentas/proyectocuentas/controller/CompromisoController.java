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

import com.cuentas.proyectocuentas.model.Compromiso;
import com.cuentas.proyectocuentas.service.ICompromisoService;



@Controller
@RequestMapping("/compromiso")
public class CompromisoController {
    @Autowired
    private ICompromisoService compromisoI;

    @GetMapping("/listar")
    public String listar(Model m){
        m.addAttribute("compromiso", compromisoI.findAll());
        Compromiso compromiso=new Compromiso();
        m.addAttribute("compromiso",compromiso);

        return "views/compromiso/compromiso";
    }

    @PostMapping("/add")
    public String add(@Valid Compromiso compromiso, BindingResult res, Model m){
        m.addAttribute("compromiso", compromisoI.findAll());

        if (res.hasErrors()) {
            return "views/compromiso/compromiso";
        }

        compromisoI.save(compromiso);
        return "redirect:/compromiso/listar";
    }

    @GetMapping("/delete/{idCompromiso}")
    public String delete(@PathVariable Integer idCompromiso){
        if (idCompromiso > 0) {
            compromisoI.delete(idCompromiso);
        }
        return "redirect:/compromiso/listar";
    }
}

