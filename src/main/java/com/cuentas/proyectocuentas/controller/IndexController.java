package com.cuentas.proyectocuentas.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cuentas.proyectocuentas.model.Compromiso;
import com.cuentas.proyectocuentas.model.IUsuario;
import com.cuentas.proyectocuentas.model.TipoCompromiso;
import com.cuentas.proyectocuentas.model.Usuario;
import com.cuentas.proyectocuentas.service.ICompromisoService;
import com.cuentas.proyectocuentas.service.ITipoCompromisoService;

@Controller
public class IndexController {
    @Autowired
    private IUsuario usuarioI;
    @Autowired
    private ICompromisoService compromisod;
   
    @Autowired
    private ITipoCompromisoService tipocompromisod;
    

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

       

        Compromiso compromiso=new Compromiso();
        m.addAttribute("compromiso",compromiso);

        LocalDate date=LocalDate.now();
        LocalDate notificar=date.plusDays(5);
        m.addAttribute("notificar", notificar);
        
            m.addAttribute("compromisos", compromisod.findAll());
            List<TipoCompromiso> tipocompromiso = tipocompromisod.findAll();
            m.addAttribute("tipocompromiso", tipocompromiso);
           
       
        return "views/principal-admin";
    }
    
@GetMapping("/nn/{idUsuario}")
public String nn(@PathVariable Integer idCom, Model m){
    if (idCom > 0) {
        m.addAttribute("usar","");
    }
    return "views/principal-admin";
}
}