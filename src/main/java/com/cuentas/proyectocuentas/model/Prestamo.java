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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="Prestamo")
public class Prestamo {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrestamo;

    @Column(name = "documentoPrestamista", length = 10)
    @Size(min=8, max=10)
    @NotEmpty
    private String documentoPrestamista;

    @Column(name = "nombrePrestamista", length = 50)
    @NotEmpty
    private String nombrePrestamista;

    @Column(name="fechaPagoOportuno" , length =  15)
    @NotEmpty
    private String fechaPagoOportuno;

    @Column(name = "valorPrestamo", length = 100000000)
    @NotEmpty
    private String valorPrestamo;

    
    @Column(name = "tasaPrestamo", length = 15 )
    @NotEmpty
    private String tasaPrestamo;

    @Column(name = "periodoCuota", length = 15 )
    @NotEmpty
    private String periodoCuota;

    @Column(name = "numeroCuotas", length = 50 )
    @NotEmpty
    private String numeroCuotas;

    @Column(name = "estadoPrestamo", length =10)
    @NotEmpty
    private String estadoPrestamo;

    /*Relacion de muchos a uno (Prestamo a Usuario) */

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    /*Relacion de uno a mucho (Prestamo a Abono) */
    @OneToMany(mappedBy = "prestamo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PrestamoAbono> prestamoabono;

    /*Relacion de uno a mucho (Prestamo a Abono) */
    @OneToMany(mappedBy = "prestamo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PagoPrestamo> pagoprestamo;

    public Prestamo() {
        prestamoabono= new ArrayList<PrestamoAbono>();
        pagoprestamo= new ArrayList<PagoPrestamo>();

    }

    public Prestamo(Integer idPrestamo, @Size(min = 10, max = 10) @NotEmpty String documentoPrestamista,
            @NotEmpty String nombrePrestamista, @NotEmpty String fechaPagoOportuno, @NotEmpty String valorPrestamo,
            @NotEmpty String tasaPrestamo, @NotEmpty String periodoCuota, @NotEmpty String numeroCuotas,
            String estadoPrestamo, Usuario usuario, List<PrestamoAbono> prestamoabono) {
        this.idPrestamo = idPrestamo;
        this.documentoPrestamista = documentoPrestamista;
        this.nombrePrestamista = nombrePrestamista;
        this.fechaPagoOportuno = fechaPagoOportuno;
        this.valorPrestamo = valorPrestamo;
        this.tasaPrestamo = tasaPrestamo;
        this.periodoCuota = periodoCuota;
        this.numeroCuotas = numeroCuotas;
        this.estadoPrestamo = estadoPrestamo;
        this.usuario = usuario;
        this.prestamoabono = prestamoabono;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getDocumentoPrestamista() {
        return documentoPrestamista;
    }

    public void setDocumentoPrestamista(String documentoPrestamista) {
        this.documentoPrestamista = documentoPrestamista;
    }

    public String getNombrePrestamista() {
        return nombrePrestamista;
    }

    public void setNombrePrestamista(String nombrePrestamista) {
        this.nombrePrestamista = nombrePrestamista;
    }

    public String getFechaPagoOportuno() {
        return fechaPagoOportuno;
    }

    public void setFechaPagoOportuno(String fechaPagoOportuno) {
        this.fechaPagoOportuno = fechaPagoOportuno;
    }

    public String getValorPrestamo() {
        return valorPrestamo;
    }

    public void setValorPrestamo(String valorPrestamo) {
        this.valorPrestamo = valorPrestamo;
    }

    public String getTasaPrestamo() {
        return tasaPrestamo;
    }

    public void setTasaPrestamo(String tasaPrestamo) {
        this.tasaPrestamo = tasaPrestamo;
    }

    public String getPeriodoCuota() {
        return periodoCuota;
    }

    public void setPeriodoCuota(String periodoCuota) {
        this.periodoCuota = periodoCuota;
    }

    public String getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(String numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<PrestamoAbono> getPrestamoabono() {
        return prestamoabono;
    }

    public void setPrestamoabono(List<PrestamoAbono> prestamoabono) {
        this.prestamoabono = prestamoabono;
    }

    
    

}
