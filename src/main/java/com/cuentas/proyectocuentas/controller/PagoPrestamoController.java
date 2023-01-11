package com.cuentas.proyectocuentas.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.cuentas.proyectocuentas.model.Compromiso;
import com.cuentas.proyectocuentas.model.PagoPrestamo;
import com.cuentas.proyectocuentas.model.TipoCompromiso;
import com.cuentas.proyectocuentas.service.ICompromisoService;
import com.cuentas.proyectocuentas.service.IPagoPrestamoService;
import com.cuentas.proyectocuentas.service.IPrestamoService;
import com.cuentas.proyectocuentas.service.ITipoCompromisoService;

 
@Controller
@SessionAttributes("pagoprestamo")
@RequestMapping("/pagoprestamo")
public class PagoPrestamoController {

    @Autowired
    private IPagoPrestamoService pagoprestamoI;

    @Autowired
    private IPrestamoService prestamoI; 
     
    @Autowired
    private ICompromisoService compromisod;
   
    @Autowired
    private ITipoCompromisoService tipocompromisod;

    //LISTAR
    @GetMapping("/listar/{idPrestamo}") 
    public String listar1(Model m, @PathVariable int idPrestamo){
        System.out.println(idPrestamo);
        m.addAttribute("idPrestamo", idPrestamo);
        m.addAttribute("prestamoC", prestamoI.findOne(idPrestamo));
        m.addAttribute("pagoprestamos", pagoprestamoI.findAll());
        PagoPrestamo pagoprestamo=new PagoPrestamo();
        m.addAttribute("pagoprestamo", pagoprestamo);
        m.addAttribute("prestamo", prestamoI.findAll());

        LocalDate date = LocalDate.now();
        LocalDate fechaPagoMin = date.minusWeeks(2);
        LocalDate fechaPagoMax = date.plusYears(1);

        m.addAttribute("fechaPagoMin", fechaPagoMin);
        m.addAttribute("fechaPagoMax", fechaPagoMax);

        Compromiso compromiso=new Compromiso();
        m.addAttribute("compromiso",compromiso);

        m.addAttribute("compromisos", compromisod.findAll());
        List<TipoCompromiso> tipocompromiso = tipocompromisod.findAll();
        m.addAttribute("tipocompromiso", tipocompromiso);
            

        return "views/pagoprestamo/pagoprestamo";
    }        

    //LISTAR
    @GetMapping("/listar") 
    public String listar(Model m){
        m.addAttribute("pagoprestamos", pagoprestamoI.findAll());
        PagoPrestamo pagoprestamo=new PagoPrestamo();
        m.addAttribute("pagoprestamo", pagoprestamo);
        m.addAttribute("prestamo", prestamoI.findAll());
 
        LocalDate date = LocalDate.now();
        LocalDate fechaPagoMin = date.minusWeeks(2);
        LocalDate fechaPagoMax = date.plusYears(1);

        m.addAttribute("fechaPagoMin", fechaPagoMin);
        m.addAttribute("fechaPagoMax", fechaPagoMax);

        Compromiso compromiso=new Compromiso();
        m.addAttribute("compromiso",compromiso);

        m.addAttribute("compromisos", compromisod.findAll());
        List<TipoCompromiso> tipocompromiso = tipocompromisod.findAll();
        m.addAttribute("tipocompromiso", tipocompromiso);
              

        return "views/pagoprestamo/pagoprestamo";
    }

     
    //AGREGAR
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("pagoprestamo") PagoPrestamo pagoprestamo, BindingResult res, Model m, 
    @RequestParam("file") MultipartFile imagenPago, SessionStatus status){
        m.addAttribute("pagoprestamos", pagoprestamoI.findAll());
        m.addAttribute("prestamo", prestamoI.findAll());
        int idSuccess=0;
        if (res.hasErrors()) {
            return "views/pagoprestamo/pagoprestamo";
        }
        if(!imagenPago.isEmpty()){
            Path directorioImagenes = Paths.get("src//main//resources//static//assets/pimg/");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = imagenPago.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta +"//"+ imagenPago.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                pagoprestamo.setImagenPago(imagenPago.getOriginalFilename());
                idSuccess=0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Integer valorprestamo=pagoprestamo.getPrestamo().getValorPrestamo()-pagoprestamo.getTotalPago();
        pagoprestamo.getPrestamo().setValorPrestamo(valorprestamo);
        pagoprestamoI.save(pagoprestamo);
        idSuccess=pagoprestamo.getIdPagoPrestamo();
        return "redirect:listar?success="+idSuccess;
    }
    

    @GetMapping("/formulario")
    public String formulario(Model m){
        PagoPrestamo pagoprestamo = new PagoPrestamo();
        m.addAttribute("pagoprestamo", pagoprestamo);
        m.addAttribute("prestamo", prestamoI.findAll());
        return "views/pagoprestamo/registrar";
    }
 
    //EDITAR
    @GetMapping(path = {"/editar/{idPagoPrestamo}", "/Editar/{idPagoPrestamo}"})
    public String editar (@PathVariable Integer idPagoPrestamo, Model m){
        PagoPrestamo pagoprestamo=null;
        if(idPagoPrestamo > 0){
            pagoprestamo=pagoprestamoI.findOne(idPagoPrestamo);
        }
        m.addAttribute("pagoprestamo", pagoprestamo);
        m.addAttribute("prestamo", prestamoI.findAll());
        return "views/pagoprestamo/editar"; 
     }
 

    //ELIMINAR
    @GetMapping("/delete/{idPagoPrestamo}")
    public String delete(@PathVariable Integer idPagoPrestamo){
        if (idPagoPrestamo > 0) {
            PagoPrestamo pago = pagoprestamoI.findOne(idPagoPrestamo);
            Integer valorprestamo=pago.getPrestamo().getValorPrestamo()+pago.getTotalPago();
            pago.getPrestamo().setValorPrestamo(valorprestamo);
            pagoprestamoI.save(pago);

            pagoprestamoI.delete(idPagoPrestamo);
        }
        return "redirect:../listar";
    }
}
 

