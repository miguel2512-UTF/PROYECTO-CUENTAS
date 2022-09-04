package com.cuentas.proyectocuentas.controller;

import java.util.List;

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

import com.cuentas.proyectocuentas.model.Compromiso;
import com.cuentas.proyectocuentas.model.TipoCompromiso;
import com.cuentas.proyectocuentas.model.Usuario;
import com.cuentas.proyectocuentas.service.ICompromisoService;
import com.cuentas.proyectocuentas.service.ITipoCompromisoService;
import com.cuentas.proyectocuentas.service.IUsuarioService;

@Controller
@SessionAttributes("compromiso")
@RequestMapping("/compromiso")
public class CompromisoController {
    @Autowired
    private ICompromisoService compromisod;
    @Autowired
    private IUsuarioService usuarioI;
    @Autowired
    private ITipoCompromisoService tipocompromisod;
    
    
   
    
        @GetMapping("/registrar")     
        public String registrar(Model m){
            Compromiso compromiso=new Compromiso();
            List<TipoCompromiso> tipocompromiso = tipocompromisod.findAll();
m.addAttribute("tipocompromiso", tipocompromiso);
List<Usuario> usuario = usuarioI.findAll();
m.addAttribute("usuario",usuario);
            m.addAttribute("compromiso",compromiso);
            m.addAttribute("accion","Registrar Compromiso");
            return "views/compromiso/registrar"; 
        }
    
        @PostMapping("/reg")
        public String reg(@Valid Compromiso compromiso, BindingResult res, Model m, SessionStatus status) throws Exception{
            if(res.hasErrors()){
                return "views/compromiso/registrar";
                }else{
             compromisod.save(compromiso);
             status.setComplete();
             return "redirect:listar";
        }}
    
        @GetMapping(path={"/listar"})
        public String listar(Model m){
            m.addAttribute("compromiso", compromisod.findAll());
            return "views/compromiso/listar";    
        }
    
        @GetMapping("/actualizar/{idCom}")
        public String editar(@PathVariable Integer idCom,Model m){
        Compromiso compromiso=null;
        if(idCom>0){
        compromiso=compromisod.findOne(idCom);
        }else{
        return "redirect:listar";
        }
        List<TipoCompromiso> tipocompromiso = tipocompromisod.findAll();
m.addAttribute("tipocompromiso", tipocompromiso);
List<Usuario> usuario = usuarioI.findAll();
m.addAttribute("usuario",usuario);
            m.addAttribute("compromiso",compromiso);
            m.addAttribute("accion","Actualizar Compromiso");
            return "views/compromiso/registrar"; 
    }
    
    @GetMapping("/delete/{idCom}")
    public String delete(@PathVariable Integer idCom) {
    if(idCom > 0) {
    compromisod.delete(idCom);
    
    }
    
    return "redirect:../listar"; 
    }
    
}
