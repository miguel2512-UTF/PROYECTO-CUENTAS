package com.cuentas.proyectocuentas.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cuentas.proyectocuentas.model.Compromiso;
import com.cuentas.proyectocuentas.model.IUsuario;
import com.cuentas.proyectocuentas.model.TipoCompromiso;
import com.cuentas.proyectocuentas.model.Usuario;
import com.cuentas.proyectocuentas.service.ICompromisoService;
import com.cuentas.proyectocuentas.service.IPrestamoService;
import com.cuentas.proyectocuentas.service.ITipoCompromisoService;

@Controller
public class IndexController {
    @Autowired
    private IUsuario usuarioI;

    @Autowired
    private ICompromisoService compromisod;
   
    @Autowired
    private ITipoCompromisoService tipocompromisod;

    @Autowired
    private IPrestamoService prestamoI;

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
    public String inicio( Model m,  Authentication auth, HttpSession session){

        String username = auth.getName();

        if (session.getAttribute("usuario")==null) {
            Usuario usuario = usuarioI.findByCorreoUsuario(username);
            usuario.setContrasenaUsuario(null);
            session.setAttribute("usuario", usuario);    
        }

        if (usuarioI.findByCorreoUsuario(username).getEstadoUsuario().equalsIgnoreCase("inactivo")) {
            return "error/inactivo";
        }

        Compromiso compromiso=new Compromiso();
        m.addAttribute("compromiso",compromiso);

        LocalDate date=LocalDate.now();
        LocalDate notificar=date.plusDays(5);
        m.addAttribute("notificar", notificar);
        m.addAttribute("compromisos", compromisod.findAll());
        // Compromiso[]comp=new Compromiso[compromisod.findAll().size()];
        // TipoCompromiso tipocom=new TipoCompromiso();
        // List<Compromiso>compr=compromisod.findAll();
        // for(int i=0;i<compr.toArray().length;i++){
        //     comp[i]=compr.get(i);
        //     Usuario user=new Usuario();
        //     user.setIdUsuario(comp[i].getUsuario().getIdUsuario());
        //     comp[i].setUsuario(user);
        //     comp[i].setTipocompromiso(tipocom);
        // }
            
        // System.out.println(comp.length);
        // System.out.println(comp[0].getUsuario().getNombresUsuario());
        // m.addAttribute("comp", comp);
        List<TipoCompromiso> tipocompromiso = tipocompromisod.findAll();
        m.addAttribute("tipocompromiso", tipocompromiso);
           
        m.addAttribute("prestamos", prestamoI.findAll());

        return "views/principal-admin";
    }
    

}