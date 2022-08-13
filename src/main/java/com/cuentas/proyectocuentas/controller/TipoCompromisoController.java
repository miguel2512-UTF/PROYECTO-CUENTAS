public package com.cuentas.proyectocuentas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cuentas.proyectocuentas.model.TipoCompromiso;
import com.cuentas.proyectocuentas.service.ITipoCompromisoService;

@Controller
@SessionAttributes("tipocompromiso")
@RequestMapping("/tipocompromiso")

public class TipoCompromisoController {
    
    @Autowired
    private ITipoCompromisoService tipocompromisod;
        
        @GetMapping(path={"/listar","","/"})
        public String listar(Model m){
            m.addAttribute("tipocompromiso", tipocompromisod.findAll());
            return "views/tipocompromiso/listar";
            
        }
      
        @GetMapping("/form")
        public String form(Model m){
            TipoCompromiso tipocompromiso=new TipoCompromiso();
        m.addAttribute("tipocompromisod", tipocompromiso);
        m.addAttribute("accion",
    "Registrar tipocompromiso");
        return "views/tipocompromiso/form";
        }
    
    
    
        @PostMapping("/add")
        public String add(@Valid TipoCompromiso tipocompromiso,BindingResult res, Model m,SessionStatus status){
            if(res.hasErrors()){
                return "/views/tipocompromiso/form";
            }
            tipocompromisod.save(tipocompromiso);
            status.setComplete();
            return "redirect:listar";
        }    
       
        
        @GetMapping("/editar/{id}")
        public String editar(@PathVariable Integer id,Model m){
            TipoCompromiso tipocompromiso=null;
            if(id>0){
                tipocompromiso=tipocompromisod.findOne(id);
            }else{
                return "redirect:listar";
            }
            m.addAttribute("tipocompromiso",tipocompromiso);
            m.addAttribute("accion", "Actualizar tipocompromiso");
            return "views/tipocompromiso/form";
        }
        @GetMapping("/delete/{id}")
        public String delete(@PathVariable Integer id) {
            
            if(id > 0) {
                tipocompromisod.delete(id);
            }
            return "redirect:../listar";
        }}

