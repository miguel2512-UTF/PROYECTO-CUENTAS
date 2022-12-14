package com.cuentas.proyectocuentas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="Usuario")
public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(length = 30, nullable = false)
    @NotEmpty
    @Pattern(regexp = "[À-ÿA-Za-zñ ]{1,20}")
    private String nombresUsuario;

    @Column(length = 30, nullable = false)
    @NotEmpty
    @Pattern(regexp = "[A-Za-zñ ]{1,20}")
    private String apellidosUsuario;

    @Column(length = 300, nullable = false)
    // @NotEmpty
    private String contrasenaUsuario;

    @Column(length = 50, nullable = false, unique = true)
    @NotEmpty
    @Email
    private String correoUsuario;

    @Column(length = 15, nullable = false)
    @NotEmpty
    private String tipoUsuario;

    @Column(length = 10, nullable = false)
    @NotEmpty
    private String estadoUsuario;

    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    private List<Compromiso> compromiso;


    /*Relacion de uno a mucho(Usuario a Prestamo) */

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Prestamo> prestamo;


    public Usuario() {
        prestamo=new ArrayList<Prestamo>();
    }

    public Usuario(int idUsuario, String nombresUsuario, String apellidosUsuario, String contrasenaUsuario, String correoUsuario,
            String tipoUsuario, String estadoUsuario) {
        this.idUsuario = idUsuario;
        this.nombresUsuario = nombresUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.correoUsuario = correoUsuario;
        this.tipoUsuario = tipoUsuario;
        this.estadoUsuario = estadoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombresUsuario() {
        return nombresUsuario;
    }

    public void setNombresUsuario(String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }
}
