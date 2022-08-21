package com.cuentas.proyectocuentas.model;

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

@Entity
@Table(name="Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(length = 30, nullable = false)
    @NotEmpty
    private String nombreUsuario;

    @Column(length = 15, nullable = false)
    @NotEmpty
    private String contrasenaUsuario;

    @Column(length = 50, nullable = false)
    @NotEmpty
    @Email
    private String correoUsuario;

    @Column(length = 15, nullable = false)
    @NotEmpty
    private String tipoUsuario;

    @Column(length = 10, nullable = false)
    @NotEmpty
    private String estadoUsuario;

    @OneToMany(mappedBy =  "usuario",fetch = FetchType.LAZY,cascade=CascadeType.ALL )
    private List<Compromiso>compromiso;

    public List<Compromiso>getCompromiso(){
      return compromiso;  
    }

    public void setCompromiso( List<Compromiso>compromiso){
        this.compromiso=compromiso;
    }

    public Usuario(List<Compromiso>compromiso) {
        this.compromiso=compromiso;  
    }

    /*Relacion de uno a mucho(Usuario a Prestamo) */

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Prestamo> prestamo;


    public Usuario() {
        compromiso=new ArrayList<Compromiso>();

        prestamo=new ArrayList<Prestamo>();
    }
    public Usuario(int idUsuario, String nombreUsuario, String contrasenaUsuario, String correoUsuario,
            String tipoUsuario, String estadoUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
