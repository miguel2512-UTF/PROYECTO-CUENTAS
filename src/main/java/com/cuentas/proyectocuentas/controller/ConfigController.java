package com.cuentas.proyectocuentas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cuentas.proyectocuentas.model.Usuario;
import com.cuentas.proyectocuentas.service.IUsuarioService;

@Controller
@RequestMapping("/settings")
public class ConfigController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioService usuarioI;
    
    @GetMapping("/listar")
    public String config(){
        return "views/config.html";
    }

    @PostMapping("/password")
    public String password(@RequestParam("idUsuario") int idUsuario,@RequestParam("oldpassword") String oldpassword, @RequestParam("newpassword") String newpassword, @RequestParam("confirmpassword") String confirmpassword, Model m){
        String[]errores={"contraseña anterior incorrecta","contraseña nueva no coincide","contraseña nueva no puede ser igual a la anterior"};
        String[]errores_new= new String[errores.length-1];
        Usuario usuario=usuarioI.findOne(idUsuario);

        if (passwordEncoder.matches(oldpassword, usuario.getContrasenaUsuario())) {
            //Contraseñas coinciden
            if (!newpassword.isBlank()) {
                if (newpassword.equals(confirmpassword)) {
                    if (newpassword.equals(oldpassword)) {
                        m.addAttribute("oldpassword", oldpassword);
                        m.addAttribute("newpassword", newpassword);
                        m.addAttribute("confirmpassword", confirmpassword);
                        m.addAttribute("valid", errores[2]);

                        return "views/config";
                    }else{
                        usuario.setContrasenaUsuario(passwordEncoder.encode(newpassword));
                        usuarioI.save(usuario); 
                    }   
                }else{
                    if (newpassword.equals(oldpassword)) {
                        for (int i = 0; i < errores_new.length; i++) {
                            errores_new[i]=errores[i+1];
                        }
                        m.addAttribute("valid", errores_new);
                    }else{
                        m.addAttribute("valid", errores[1]);
                    }   

                    m.addAttribute("oldpassword", oldpassword);
                    m.addAttribute("newpassword", newpassword);
                    m.addAttribute("confirmpassword", confirmpassword);

                    return "views/config";
                }
            }else{
                m.addAttribute("oldpassword", oldpassword);
                m.addAttribute("newpassword", newpassword);
                m.addAttribute("confirmpassword", confirmpassword);
                m.addAttribute("valid","La contraseña no puede estar vacía");

                return "views/config";
            }
        }else{
            m.addAttribute("oldpassword", oldpassword);
            m.addAttribute("newpassword", newpassword);
            m.addAttribute("confirmpassword", confirmpassword);
        
            if (newpassword.equals(confirmpassword)) {
                m.addAttribute("valid", errores[0]);
            }else{
                for (int i = 0; i < errores_new.length; i++) {
                    errores_new[i]=errores[i];
                }
                m.addAttribute("valid", errores_new);
            }

            return "views/config";
        }

        return "redirect:/settings/listar?password";
    }

    @PostMapping("/activar")
    public String activar(@RequestParam("idUsuario") int idUsuario, Authentication auth, HttpSession session){
        Usuario usuario = usuarioI.findOne(idUsuario);

        usuario.setEstadoUsuario("activo");
        usuarioI.save(usuario);

        return "redirect:/inicio?active";
    }

    @GetMapping("/test/{idUsuario}/{oldpassword}")
    @ResponseBody
    public Boolean test(@PathVariable Integer idUsuario, @PathVariable String oldpassword) {
        Usuario user = usuarioI.findOne(idUsuario);
        
        return passwordEncoder.matches(oldpassword, user.getContrasenaUsuario());
    }

}
