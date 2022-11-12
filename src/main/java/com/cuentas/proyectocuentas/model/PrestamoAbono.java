package com.cuentas.proyectocuentas.model;

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
@Table (name="PrestamoAbono")
public class PrestamoAbono {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrestamoAbono;

    @Column(name ="fechaAbono", length =10)
    @NotEmpty
    private String fechaAbono;
 
    @Column(name = "totalAbono", length =10)
    @NotNull
    private Integer totalAbono;

    private String imagenAbono;

    /*Relacion de muchos a uno (Abono a Prestamo) */
    @ManyToOne(fetch = FetchType.LAZY) 
    private Prestamo prestamo;

    public PrestamoAbono() {
    }

    public PrestamoAbono(Integer idPrestamoAbono, @NotEmpty String fechaAbono, @NotEmpty Integer totalAbono,
            String imagenAbono, Prestamo prestamo) {
        this.idPrestamoAbono = idPrestamoAbono;
        this.fechaAbono = fechaAbono;
        this.totalAbono = totalAbono;
        this.imagenAbono = imagenAbono;
        this.prestamo = prestamo;
    }

    public Integer getIdPrestamoAbono() {
        return idPrestamoAbono;
    }

    public void setIdPrestamoAbono(Integer idPrestamoAbono) {
        this.idPrestamoAbono = idPrestamoAbono;
    }

    public String getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(String fechaAbono) {
        this.fechaAbono = fechaAbono;
    }

    public Integer getTotalAbono() {
        return totalAbono;
    }

    public void setTotalAbono(Integer totalAbono) {
        this.totalAbono = totalAbono;
    }

    public String getImagenAbono() {
        return imagenAbono;
    }

    public void setImagenAbono(String imagenAbono) {
        this.imagenAbono = imagenAbono;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    
    
}
