package com.cuentas.proyectocuentas.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
@Entity
@Table(name="Compromiso")
public class Compromiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompromiso;

    @Column(length = 30, nullable = false)
    @NotEmpty
    private String tipoCompromiso;

    @Column(length = 15, nullable = false)
    @NotEmpty
    private String numeroFactura;

    @Column(length = 50, nullable = false)
    @NotEmpty
    private String nombreEmpresa;

    @Column(length = 50, nullable = false)
    @NotEmpty
    private Date fechaPagoOpor;

    @Column(length = 50, nullable = false)
    @NotEmpty
    private Date fechaSuspen;

    @Column(length = 15, nullable = false)
    @NotEmpty
    private String metodoPago;

    @Column(length = 15, nullable = false)
    @NotEmpty
    private Integer totalPago;


    @Column(length = 10, nullable = false)
    @NotEmpty
    private String estadoPago;

    public Compromiso() {
    }

    public Compromiso(Integer idCompromiso, String tipoCompromiso, String numeroFactura, String nombreEmpresa,
            Date fechaPagoOpor, Date fechaSuspen,String metodoPago, Integer totalPago, String estadoPago) {
        this.idCompromiso = idCompromiso;
        this.tipoCompromiso = tipoCompromiso;
        this.numeroFactura = numeroFactura;
        this.nombreEmpresa = nombreEmpresa;
        this.fechaPagoOpor = fechaPagoOpor;
        this.fechaSuspen = fechaSuspen;
        this.metodoPago = metodoPago;
        this.totalPago = totalPago;
        this.estadoPago = estadoPago;
        
    }

    public Integer getIdCompromiso() {
        return idCompromiso;
    }

    public void setIdCompromiso(Integer idCompromiso) {
        this.idCompromiso = idCompromiso;
    }

    public String getTipoCompromiso() {
        return tipoCompromiso;
    }

    public void setTipoCompromiso(String tipoCompromiso) {
        this.tipoCompromiso = tipoCompromiso;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Date getFechaPagoOpor() {
        return fechaPagoOpor;
    }

    public void setFechaPagoOpor(Date fechaPagoOpor) {
        this.fechaPagoOpor = fechaPagoOpor;
    }

    public Date getFechaSuspen() {
        return fechaSuspen;
    }

    public void setFechaSuspen(Date fechaSuspen) {
        this.fechaSuspen = fechaSuspen;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Integer getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Integer totalPago) {
        this.totalPago = totalPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

}