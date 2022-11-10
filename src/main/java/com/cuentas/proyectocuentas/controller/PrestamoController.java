package com.cuentas.proyectocuentas.controller; 
 
import java.time.LocalDate;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cuentas.proyectocuentas.service.IPrestamoService;
import com.cuentas.proyectocuentas.service.IUsuarioService;
import com.cuentas.proyectocuentas.model.Prestamo;
 
@Controller
@SessionAttributes("prestamo")
@RequestMapping("/prestamo")
public class PrestamoController {

    @Autowired
    private IPrestamoService prestamoI;

    @Autowired
    private IUsuarioService usuarioI;


    //LISTAR
    @GetMapping("/listar")
    public String listar(Model m){
        m.addAttribute("prestamos", prestamoI.findAll());
        Prestamo prestamo=new Prestamo();
        m.addAttribute("prestamo", prestamo);
        m.addAttribute("usuario", usuarioI.findAll());

        LocalDate date = LocalDate.now();
        LocalDate fechaPagoMin = date.minusWeeks(2);
        LocalDate fechaPagoMax = date.plusYears(1);

        m.addAttribute("fechaPagoMin", fechaPagoMin);
        m.addAttribute("fechaPagoMax", fechaPagoMax);
        
        return "views/prestamo/prestamo";
        
    }


    //AGREGAR
    @PostMapping("/add")
    public String add(@Valid Prestamo prestamo, BindingResult res, Model m, SessionStatus status){
        m.addAttribute("prestamos", prestamoI.findAll());
         
        if (res.hasErrors()) {
            return "views/prestamo/prestamo";
        }

        if (prestamo.getIdPrestamo()==null){ 

            //Convertimos de entero a DOUBLE
            Double a = Double.valueOf(prestamo.getValorPrestamo()); 
            Double tasa=(prestamo.getTasaPrestamo() * a )/100;
            Double valorprestamo=a+tasa;
            //Convertimos de double a ENTERO
            Integer valor = valorprestamo.intValue();
            prestamo.setValorPrestamo(valor);

            prestamo.setValorPrestamoInicial(prestamo.getValorPrestamo());

        }

        prestamoI.save(prestamo);
        status.setComplete();
        
        return "redirect:listar";
    }

    // @GetMapping("/formulario")
    // public String formulario(Model m){
    //     Prestamo prestamo = new Prestamo();
    //     m.addAttribute("prestamo", prestamo);
    //     return "views/prestamo/registrar";
        
    // } 


    //EDITAR
    // @GetMapping(path = {"/editar/{idPrestamo}", "/Editar/{idPrestamo}"})
    // public String editar (@PathVariable Integer idPrestamo, Model m){
    //     Prestamo prestamo=null;
    //     if(idPrestamo > 0){
    //         prestamo=prestamoI.findOne(idPrestamo);
    //     }
    //     m.addAttribute("prestamo", prestamo);
    //     return "views/prestamo/editar";
    // }

    //EDITAR 
    @GetMapping("/editar")
    @ResponseBody
    public Prestamo editar(Integer idPrestamo){
        return prestamoI.findOne(idPrestamo);
    }
 

    //ELIMINAR
    @GetMapping("/delete/{idPrestamo}")
    public String delete(@PathVariable Integer idPrestamo){
        if (idPrestamo > 0) {
            prestamoI.delete(idPrestamo);
        }
        return "redirect:../listar";
    }

   //INACTIVAR O ACTIVAR
   @GetMapping("/cambioestado/{idPrestamo}")
   public String cambioestado(@PathVariable Integer idPrestamo){
       Prestamo prestamo = new Prestamo();
       prestamo=prestamoI.findOne(idPrestamo);
       if (prestamo.getEstadoPrestamo().equalsIgnoreCase("activo")) {
           prestamo.setEstadoPrestamo("Inactivo");
           prestamoI.save(prestamo);
       }else if (prestamo.getEstadoPrestamo().equalsIgnoreCase("inactivo")) {
           prestamo.setEstadoPrestamo("Activo");
           prestamoI.save(prestamo);
       }

       return "redirect:/prestamo/listar?success=true";
   }
 
    
}
