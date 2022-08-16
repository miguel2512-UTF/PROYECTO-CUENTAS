package com.cuentas.proyectocuentas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    
    @RequestMapping(value = "/index",method = RequestMethod.GET) 
    public String index(){
        return "index.html";
    }

    @GetMapping("/usuario")
    public String usuario(){
        return "redirect:/usuario/listar";
    }

    @GetMapping("/prestamo")
    public String prestamo(){
        return "redirect:/prestamo/listar";
    }

    @GetMapping("/compromiso")
    public String compromiso(){
        return "redirect:/compromiso/listar";
    }

    @GetMapping("/inicio")
    public String inicio(){
        return "views/principal-admin";
    }
}
