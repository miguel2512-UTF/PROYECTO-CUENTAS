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

import com.cuentas.proyectocuentas.model.Compromiso;
import com.cuentas.proyectocuentas.service.ICompromisoService;



@Controller
@SessionAttributes("compromiso")
@RequestMapping("/compromiso")
public class CompromisoController {
    @Autowired
private ICompromisoService compromisod;
    
    
    @GetMapping("/compromiso")     
        public String compromiso(){
            return "views/compromiso/compromiso"; 
        }
    
        @GetMapping("/registrar")     
        public String registrar(Model m){
            Compromiso compromiso=new Compromiso();
            m.addAttribute("compromiso",compromiso);
            m.addAttribute("accion","Registrar Compromiso");
            return "views/compromiso/registrar"; 
        }
    
        @PostMapping("/reg")
        public String reg(@Valid Compromiso compromiso, BindingResult res, Model m, SessionStatus status){
            if(res.hasErrors()){
                return "views/compromiso/registrar";
                }
             compromisod.save(compromiso);
             status.setComplete();
             return "redirect:listar";
        }
    
        @GetMapping(path={"/listar"})
        public String listar(Model m){
            m.addAttribute("compromiso", compromisod.findAll());
            return "views/compromiso/listar";    
        }
    
        @GetMapping("/actualizar/{idCompromiso}")
        public String editar(@PathVariable Integer idCompromiso,Model m){
        Compromiso compromiso=null;
        if(idCompromiso>0){
        compromiso=compromisod.findOne(idCompromiso);
        }else{
        return "redirect:listar";
        }
        m.addAttribute("compromiso",compromiso);
        m.addAttribute("accion","Actualizar Compromiso");
        return "views/compromiso/registrar"; 
    }
    
    @GetMapping("/delete/{idCompromiso}")
    public String delete(@PathVariable Integer idCompromiso) {
    if(idCompromiso > 0) {
    compromisod.delete(idCompromiso);
    
    }
    
    return "redirect:../listar"; 
    }
    }
     
        
    
    



