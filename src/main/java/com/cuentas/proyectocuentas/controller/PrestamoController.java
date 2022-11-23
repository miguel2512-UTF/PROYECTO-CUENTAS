package com.cuentas.proyectocuentas.controller; 
 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
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

import com.cuentas.proyectocuentas.service.IPagoPrestamoService;
import com.cuentas.proyectocuentas.service.IPrestamoAbonoService;
import com.cuentas.proyectocuentas.service.IPrestamoService;
import com.cuentas.proyectocuentas.service.IUsuarioService;
import com.cuentas.proyectocuentas.model.PagoPrestamo;
import com.cuentas.proyectocuentas.model.Prestamo;
import com.cuentas.proyectocuentas.model.PrestamoAbono;
 
@Controller
@SessionAttributes("prestamo")
@RequestMapping("/prestamo")
public class PrestamoController {

    @Autowired
    private IPrestamoService prestamoI;

    @Autowired
    private IUsuarioService usuarioI;

    @Autowired
    private IPagoPrestamoService pagoprestamoI;

    @Autowired
    private IPrestamoAbonoService prestamoabonoI;



    //LISTAR
    @GetMapping("/listar")
    public String listar(Model m){
        m.addAttribute("prestamos", prestamoI.findAll());
        Prestamo prestamo=new Prestamo();
        m.addAttribute("prestamo", prestamo);
        m.addAttribute("usuario", usuarioI.findAll());
        m.addAttribute("abonos", prestamoabonoI.findAll());
        m.addAttribute("pagos", pagoprestamoI.findAll());

        //Pagos y Abonos
        // PagoPrestamo pagoprestamo = new PagoPrestamo();
        // PrestamoAbono prestamoabono = new PrestamoAbono();
        // m.addAttribute("pagoprestamo", pagoprestamo);
        // m.addAttribute("prestamoabono", prestamoabono);


        LocalDate date = LocalDate.now();
        LocalDate fechaPagoMin = date.minusWeeks(2);
        LocalDate fechaPagoMax = date.plusYears(1);

        m.addAttribute("fechaPagoMin", fechaPagoMin);
        m.addAttribute("fechaPagoMax", fechaPagoMax);       

        return "views/prestamo/prestamo";

        
    }
 

    //AGREGAR PRÉSTAMO
    @PostMapping("/add")
    public String add(@Valid Prestamo prestamo, BindingResult res, Model m, SessionStatus status){
        m.addAttribute("prestamos", prestamoI.findAll());
        int idSuccess=0;
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
            idSuccess=0;

        }

        prestamoI.save(prestamo);
        idSuccess=prestamo.getIdPrestamo();

        status.setComplete();
 
        return "redirect:listar?success="+idSuccess;
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

    //REGISTRAR PAGÓ

    @PostMapping("/addPago")
    public String addPago(@Valid @ModelAttribute("pagoprestamo") PagoPrestamo pagoprestamo, BindingResult res, Model m, 
    @RequestParam("file") MultipartFile imagenPago, SessionStatus status){
        m.addAttribute("pagoprestamos", pagoprestamoI.findAll());
        m.addAttribute("prestamo", prestamoI.findAll());

        if (res.hasErrors()) {
            return "views/prestamo/prestamo";
        } 
        if(!imagenPago.isEmpty()){
            Path directorioImagenes = Paths.get("src//main//resources//static//assets/pimg/");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = imagenPago.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta +"//"+ imagenPago.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                pagoprestamo.setImagenPago(imagenPago.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Integer valorprestamo=pagoprestamo.getPrestamo().getValorPrestamo()-pagoprestamo.getTotalPago();
        pagoprestamo.getPrestamo().setValorPrestamo(valorprestamo);
        pagoprestamoI.save(pagoprestamo);

        return "redirect:listar";
    }

    //REGISTRAR ABONO
    @PostMapping("/addAbono")
    public String addAbono(@Valid @ModelAttribute("abono") PrestamoAbono abono, BindingResult res, Model m, 
     @RequestParam("file") MultipartFile imagenAbono ,SessionStatus status){
        m.addAttribute("prestamoabonos", prestamoabonoI.findAll());
        m.addAttribute("prestamo", prestamoI.findAll());

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
                
            } catch (Exception e) {
                e.printStackTrace();
            }
     
        }
        Integer valorprestamo=abono.getPrestamo().getValorPrestamo()-abono.getTotalAbono();
        abono.getPrestamo().setValorPrestamo(valorprestamo);
        prestamoabonoI.save(abono);
        status.setComplete(); 

        return "redirect:listar";
    }

    

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
