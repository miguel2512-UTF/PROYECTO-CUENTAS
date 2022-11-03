package com.cuentas.proyectocuentas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cuentas.proyectocuentas.model.IUsuario;
import com.cuentas.proyectocuentas.model.Usuario;

@Controller
public class IndexController {
    @Autowired
    private IUsuario usuarioI;

    @RequestMapping(value = "/",method = RequestMethod.GET) 
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

    @GetMapping("/prestamoabono")
    public String prestamoabono(){
        return "redirect:/prestamoabono/listar";
    }

    @GetMapping("/pagoprestamo")
    public String pagoprestamo(){
        return "redirect:/pagoprestamo/listar";
    }

    @GetMapping("/compromiso")
    public String compromiso(){
        return "redirect:/compromiso/listar";
    }

    @GetMapping("/tipocompromiso")
    public String tipocompromiso(){
        return "redirect:/tipocompromiso/listar";
    }

    @GetMapping("/login")
    public String login(Model m) {
        m.addAttribute("usuario", new Usuario());
        return "login";
    }

    @GetMapping("/inicio")
    public String inicio(Authentication auth, HttpSession session){

        String username = auth.getName();

        if (session.getAttribute("usuario")==null) {
            Usuario usuario = usuarioI.findByCorreoUsuario(username);
            usuario.setContrasenaUsuario(null);
            session.setAttribute("usuario", usuario);
        }

        return "views/principal-admin";
    }
}
