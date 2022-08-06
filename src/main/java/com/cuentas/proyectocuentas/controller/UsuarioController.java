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

import com.cuentas.proyectocuentas.model.Usuario;
import com.cuentas.proyectocuentas.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioI;

    @GetMapping("/listar")
    public String listar(Model m){
        m.addAttribute("usuarios", usuarioI.findAll());
        Usuario usuario=new Usuario();
        m.addAttribute("usuario",usuario);

        return "views/usuario/usuario";
    }

    @PostMapping("/add")
    public String add(@Valid Usuario usuario, BindingResult res, Model m){
        m.addAttribute("usuarios", usuarioI.findAll());

        if (res.hasErrors()) {
            return "views/usuario/usuario";
        }

        usuarioI.save(usuario);
        return "redirect:/usuario/listar";
    }

    @GetMapping("/delete/{idUsuario}")
    public String delete(@PathVariable Integer idUsuario){
        if (idUsuario > 0) {
            usuarioI.delete(idUsuario);
        }
        return "redirect:/usuario/listar";
    }
}
