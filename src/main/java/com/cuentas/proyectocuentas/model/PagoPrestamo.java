package com.cuentas.proyectocuentas.model;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PagoPrestamo")
public class PagoPrestamo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPagoPrestamo;

    @Column(name ="fechaPago", length =10)
    @NotEmpty
    private String fechaPago;
 
    @Column(name = "totalPago", length =10)
    @NotEmpty
    private String totalPago;
 
    @Column(name = "imagenPago")
    private File imagenPago;

    /*Relacion de muchos a uno (Pago a Prestamo) */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Prestamo prestamo;

    public PagoPrestamo() {

    }

    public PagoPrestamo(Integer idPagoPrestamo, @NotEmpty String fechaPago, @NotEmpty String totalPago,
            @NotEmpty File imagenPago, Prestamo prestamo) {
        this.idPagoPrestamo = idPagoPrestamo;
        this.fechaPago = fechaPago;
        this.totalPago = totalPago;
        this.imagenPago = imagenPago;
        this.prestamo = prestamo;
    }

    public Integer getIdPagoPrestamo() {
        return idPagoPrestamo;
    }

    public void setIdPagoPrestamo(Integer idPagoPrestamo) {
        this.idPagoPrestamo = idPagoPrestamo;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(String totalPago) {
        this.totalPago = totalPago;
    }

    public File getImagenPago() {
        return imagenPago;
    }

    public void setImagenPago(File imagenPago) {
        this.imagenPago = imagenPago;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    
    
}
