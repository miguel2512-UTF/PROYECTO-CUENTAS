package com.cuentas.proyectocuentas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Prestamo")
public class Prestamo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrestamo;

    @Column(name = "documentoPrestamista", length = 10)
    @NotEmpty
    private String documentoPrestamista;

    @Column(name = "nombrePrestamista", length = 50)
    @NotEmpty
    private String nombrePrestamista;

    @Column(name="fechaPagoOportuno" , length =  15)
    @NotEmpty
    private String fechaPagoOportuno;

    @Column(name = "valorPrestamo", nullable = false)
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

    @Column(name = "estadoPrestamo", nullable = true)
    private Boolean estadoPrestamo;

    public Prestamo() {
    }

    public Prestamo(Integer idPrestamo, @NotEmpty String documentoPrestamista, @NotEmpty String nombrePrestamista,
            @NotEmpty String fechaPagoOportuno, @NotEmpty String valorPrestamo, @NotEmpty String tasaPrestamo,
            @NotEmpty String periodoCuota, @NotEmpty String numeroCuotas, Boolean estadoPrestamo) {
        this.idPrestamo = idPrestamo;
        this.documentoPrestamista = documentoPrestamista;
        this.nombrePrestamista = nombrePrestamista;
        this.fechaPagoOportuno = fechaPagoOportuno;
        this.valorPrestamo = valorPrestamo;
        this.tasaPrestamo = tasaPrestamo;
        this.periodoCuota = periodoCuota;
        this.numeroCuotas = numeroCuotas;
        this.estadoPrestamo = estadoPrestamo;
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

    public Boolean getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(Boolean estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    

    

}
