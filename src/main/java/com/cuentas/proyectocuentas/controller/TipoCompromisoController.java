package com.cuentas.proyectocuentas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cuentas.proyectocuentas.model.TipoCompromiso;
import com.cuentas.proyectocuentas.service.ITipoCompromisoService;

@Controller
@SessionAttributes("tipocompromiso")
@RequestMapping("/tipocompromiso")
public class TipoCompromisoController {
    
    @Autowired
    private ITipoCompromisoService tipocompromisod;
      
        @GetMapping("/form")
        public String form(Model m){
            TipoCompromiso tipocompromiso=new TipoCompromiso();
            m.addAttribute("tipocompromiso", tipocompromiso);
            m.addAttribute("accion","Registrar tipocompromiso");
            return "views/tipocompromiso/form";
        }
     
        @GetMapping("/listar")
        public String listar(Model m){
            m.addAttribute("tipocompromisos", tipocompromisod.findAll());
            TipoCompromiso tipocompromiso=new TipoCompromiso();
            m.addAttribute("tipocompromiso",tipocompromiso);

            return "views/tipocompromiso/tipocompromiso";
        }
    
        @PostMapping("/add")
        public String add(@Valid @ModelAttribute("tipocompromiso") TipoCompromiso tipocompromiso,BindingResult res, Model m,SessionStatus status){
            m.addAttribute("tipocompromisos", tipocompromisod.findAll());
            m.addAttribute("tipocompromiso", tipocompromiso);

            if(res.hasErrors()){
                try {
                    tipocompromisod.createTipoCompromiso(tipocompromiso);
                } catch (Exception e) {
                    if (e.getMessage().equalsIgnoreCase("NOMBRE no disponible")) {
                        m.addAttribute("errorMessage",e.getMessage());
                    }
                }
                return "views/tipocompromiso/tipocompromiso";
            } 
            
            try {
                tipocompromisod.createTipoCompromiso(tipocompromiso);
             } catch (Exception e) {
                 m.addAttribute("errorMessage",e.getMessage());
                 return "views/tipocompromiso/tipocompromiso";
             } 
          status.setComplete();
            return "redirect:listar";
        }    
        @PostMapping("/reg1")
    public String reg1(@Valid TipoCompromiso tipocompromiso, BindingResult res, Model m, SessionStatus status)
            throws Exception {
        m.addAttribute("tipocompromisos", tipocompromisod.findAll());

        if (res.hasErrors()) {
            m.addAttribute("modalEdit","");
            return "views/tipocompromiso/tipocompromiso";
        }
        tipocompromisod.save(tipocompromiso);
        status.setComplete();

        return "redirect:listar";
    }
    @GetMapping("/editar")
    @ResponseBody
    public TipoCompromiso editar(Integer id) {
        return tipocompromisod.findOne(id);
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
        }

        @GetMapping("/estado/{id}")
        public String estado(@PathVariable Integer id){
            TipoCompromiso tipocompromiso = new TipoCompromiso();
            tipocompromiso=tipocompromisod.findOne(id);
            if (tipocompromiso.getEstado().equalsIgnoreCase("activo")) {
                tipocompromiso.setEstado("Inactivo");
                tipocompromisod.save(tipocompromiso);
            }else if (tipocompromiso.getEstado().equalsIgnoreCase("inactivo")) {
                tipocompromiso.setEstado("Activo");
                tipocompromisod.save(tipocompromiso);
            }
            return "redirect:/tipocompromiso/listar";
        }

}

