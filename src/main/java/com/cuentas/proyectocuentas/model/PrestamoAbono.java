package com.cuentas.proyectocuentas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
    @NotEmpty
    private Double totalAbono;

    public PrestamoAbono() {
    }

    public PrestamoAbono(Integer idPrestamoAbono, @NotEmpty String fechaAbono, @NotEmpty Double totalAbono) {
        this.idPrestamoAbono = idPrestamoAbono;
        this.fechaAbono = fechaAbono;
        this.totalAbono = totalAbono;
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

    public Double getTotalAbono() {
        return totalAbono;
    }

    public void setTotalAbono(Double totalAbono) {
        this.totalAbono = totalAbono;
    }

    

    
}
