package com.cuentas.proyectocuentas.controller;
 
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import com.cuentas.proyectocuentas.model.Compromiso;
import com.cuentas.proyectocuentas.model.TipoCompromiso;

import com.cuentas.proyectocuentas.model.PrestamoAbono;
import com.cuentas.proyectocuentas.service.ICompromisoService;
import com.cuentas.proyectocuentas.service.IPrestamoAbonoService;
import com.cuentas.proyectocuentas.service.IPrestamoService;
import com.cuentas.proyectocuentas.service.ITipoCompromisoService;
 
@Controller
@SessionAttributes("prestamoabono")
@RequestMapping("/prestamoabono")
public class PrestamoAbonoController {

    @Autowired
    private IPrestamoAbonoService prestamoabonoI;

    @Autowired
    private IPrestamoService prestamoI;
    @Autowired
    private ICompromisoService compromisod;
   
    @Autowired
    private ITipoCompromisoService tipocompromisod;

    //LISTAR
    @GetMapping("/listar")
    public String listar(Model m){
        m.addAttribute("prestamoabonos", prestamoabonoI.findAll());
        PrestamoAbono prestamoabono=new PrestamoAbono();
        m.addAttribute("abono", prestamoabono);
        m.addAttribute("prestamo", prestamoI.findAll());

        LocalDate date=LocalDate.now();
        LocalDate fechaAbonoMin=date.minusWeeks(2);
        LocalDate fechaAbonoMax = date.plusYears(1);
 
        m.addAttribute("fechaAbonoMin", fechaAbonoMin);
        m.addAttribute("fechaAbonoMax", fechaAbonoMax);

        Compromiso compromiso=new Compromiso();
        m.addAttribute("compromiso",compromiso);

        m.addAttribute("compromisos", compromisod.findAll());
        List<TipoCompromiso> tipocompromiso = tipocompromisod.findAll();
        m.addAttribute("tipocompromiso", tipocompromiso);
              
        return "views/prestamoabono/prestamoabono";
    }

    //AGREGAR
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("abono") PrestamoAbono abono, BindingResult res, Model m, 
     @RequestParam("file") MultipartFile imagenAbono ,SessionStatus status){
        m.addAttribute("prestamoabonos", prestamoabonoI.findAll());
        m.addAttribute("prestamo", prestamoI.findAll());
        int idSuccess=0;
        if (res.hasErrors()) {
            return "views/prestamoabono/prestamoabono";
        }

        if(!imagenAbono.isEmpty()){
            Path directorioImagenes = Paths.get("src//main//resources//static//assets/aimg/");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg= imagenAbono.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta +"//" + imagenAbono.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                abono.setImagenAbono(imagenAbono.getOriginalFilename());
                idSuccess=0;
            } catch (Exception e) {
                e.printStackTrace();
            }
     
        }
        Integer valorprestamo=abono.getPrestamo().getValorPrestamo()-abono.getTotalAbono();
        abono.getPrestamo().setValorPrestamo(valorprestamo);
        prestamoabonoI.save(abono);
        status.setComplete(); 
        idSuccess=abono.getIdPrestamoAbono();

        return "redirect:listar?success="+idSuccess;
    }

    // @GetMapping("/formulario")
    // public String formulario(Model m){
    //     PrestamoAbono prestamoabono = new PrestamoAbono();
    //     m.addAttribute("prestamoabono", prestamoabono);
    //     m.addAttribute("prestamo", prestamoI.findAll());
    //     return "views/prestamoabono/registrar";
    // }

    //EDITAR
    // @GetMapping(path = {"/editar/{idPrestamoAbono}", "/Editar/{idPrestamoAbono}"})
    // public String editar (@PathVariable Integer idPrestamoAbono, Model m){
    //     PrestamoAbono prestamoabono=null;
    //     if(idPrestamoAbono > 0){
    //         prestamoabono=prestamoabonoI.findOne(idPrestamoAbono);
    //     }
    //     m.addAttribute("prestamoabono", prestamoabono);
    //     m.addAttribute("prestamo", prestamoI.findAll());
    //     return "views/prestamoabono/editar"; 
    // }

    //EDITAR

    @GetMapping("/editar")
    @ResponseBody
    public PrestamoAbono editar(Integer idPrestamoAbono){
        return prestamoabonoI.findOne(idPrestamoAbono);
    }

 

    //ELIMINAR
    @GetMapping("/delete/{idPrestamoAbono}")
    public String delete(@PathVariable Integer idPrestamoAbono){
        if (idPrestamoAbono > 0) {
            PrestamoAbono abono = prestamoabonoI.findOne(idPrestamoAbono);
            Integer valorprestamo= abono.getPrestamo().getValorPrestamo()+abono.getTotalAbono();
            abono.getPrestamo().setValorPrestamo(valorprestamo);
            prestamoabonoI.save(abono);

            prestamoabonoI.delete(idPrestamoAbono);
        }
        return "redirect:../listar";
    }

    
}

