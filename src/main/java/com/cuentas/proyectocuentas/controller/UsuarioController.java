package com.cuentas.proyectocuentas.controller;

import javax.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cuentas.proyectocuentas.model.Compromiso;
import com.cuentas.proyectocuentas.model.TipoCompromiso;
import com.cuentas.proyectocuentas.model.Usuario;
import com.cuentas.proyectocuentas.service.IUsuarioService;
import com.cuentas.proyectocuentas.service.ITipoCompromisoService;
import com.cuentas.proyectocuentas.service.ICompromisoService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioI;

    @Autowired
    private ICompromisoService compromisod;
   
    @Autowired
    private ITipoCompromisoService tipocompromisod;

    @GetMapping("/listar")
    public String listar(Model m){
        m.addAttribute("usuarios", usuarioI.findAll());
        Usuario usuario=new Usuario();
        m.addAttribute("usuario",usuario);

        Compromiso compromiso=new Compromiso();
        m.addAttribute("compromiso",compromiso);

        m.addAttribute("compromisos", compromisod.findAll());
        List<TipoCompromiso> tipocompromiso = tipocompromisod.findAll();
        m.addAttribute("tipocompromiso", tipocompromiso);
        
        return "views/usuario/usuario";
    }

    @PostMapping("/add")
    public String add(@Valid Usuario usuario, BindingResult res, Model m){
        m.addAttribute("usuarios", usuarioI.findAll());
        String success="";
        if (res.hasErrors()) {
            if (usuario.getIdUsuario()==0) {
                m.addAttribute("modalAdd","");
                try {
                    usuarioI.createUser(usuario);
                } catch (Exception e) {
                    if (e.getMessage().equalsIgnoreCase("username no disponible") || e.getMessage().equalsIgnoreCase("email no disponible")) {
                        m.addAttribute("errorMessage",e.getMessage());
                    }
                }
            }else if (usuario.getIdUsuario()>0) {
                m.addAttribute("modalEdit", "");
            }

            return "views/usuario/usuario";
        }

        if (usuario.getIdUsuario()==0) {
            usuario.setContrasenaUsuario(usuario.getCorreoUsuario());
            usuario.setNombresUsuario(usuario.getNombresUsuario().toLowerCase());
            usuario.setApellidosUsuario(usuario.getApellidosUsuario().toLowerCase());
            try {
                usuarioI.createUser(usuario);
                success="create";
            } catch (Exception e) {
                m.addAttribute("modalAdd","");
                m.addAttribute("errorMessage",e.getMessage());
                return "views/usuario/usuario";
            } 
        }else if (usuario.getIdUsuario()>0) {
            usuarioI.save(usuario);
            success="update";
        }
        
        return "redirect:/usuario/listar?"+success;
    }

    @GetMapping("/editar")
    @ResponseBody
    public Usuario editar(Integer idUsuario){
        return usuarioI.findOne(idUsuario);
    }

    @GetMapping("/delete/{idUsuario}")
    public String delete(@PathVariable Integer idUsuario){
        if (idUsuario > 0) {
            usuarioI.delete(idUsuario);
        }
        return "redirect:/usuario/listar";
    }
 
    @GetMapping("/cambioestado/{idUsuario}")
    public String cambioestado(@PathVariable Integer idUsuario){
        Usuario usuario = new Usuario();
        usuario=usuarioI.findOne(idUsuario);
        if (usuario.getEstadoUsuario().equalsIgnoreCase("activo")) {
            usuario.setEstadoUsuario("Inactivo");
            usuarioI.save(usuario);
        }else if (usuario.getEstadoUsuario().equalsIgnoreCase("inactivo")) {
            usuario.setEstadoUsuario("Activo");
            usuarioI.save(usuario);
        }

        return "redirect:/usuario/listar?update";
    }

    @GetMapping("/registrar")
    public String registrar(Model m){
        m.addAttribute("usuario", new Usuario());

        return "views/usuario/usuario-register";
    }
    
    // @GetMapping("/login")
    // public String login(Model m){
    //     m.addAttribute("usuario", new Usuario());
    //     return "login";
    // }
    
    // @PostMapping("/iniciars")
    // public String iniciars(Usuario usuario, Model m){
    //     Usuario usu=new Usuario();

    //     if (usuarioI.login(usuario)!=null) {
    //         usu=usuarioI.login(usuario);
    //     }

    //     if (usuario.getNombresUsuario().equalsIgnoreCase(usu.getNombresUsuario()) && usuario.getNombresUsuario()!=null) {
    //         if (usuario.getContrasenaUsuario().equalsIgnoreCase(usu.getContrasenaUsuario()) && usuario.getContrasenaUsuario()!=null) {
    //            return "redirect:/inicio"; 
    //         }
    //     }else{
    //         m.addAttribute("Error","Contrase??a o usuario incorrecto");
    //         return "login";
    //     }
    //     return null;
    // }
}
