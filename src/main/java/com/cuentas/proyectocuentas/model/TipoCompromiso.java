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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="tipocompromiso")
public class TipoCompromiso {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=2,max=50)
    @Column(length = 20,nullable=false, unique = true)
    @NotEmpty
    private String nombre;

    @Column(length = 200,nullable=false)
    @NotEmpty
    private String descripcion;

    @Column(nullable = false)
    @NotEmpty
    private String estado;

    @OneToMany(mappedBy = "tipocompromiso",fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    private List<Compromiso> compromiso;



	public List<Compromiso> getCompromiso() {
		return compromiso;
	}

	public void setCompromiso(List<Compromiso> compromiso) {
		this.compromiso = compromiso;
	}

	public TipoCompromiso(List<Compromiso> compromiso) {
		this.compromiso = compromiso;
	}
   
    public TipoCompromiso(){
        compromiso=new ArrayList<Compromiso>();
        
    }

    public TipoCompromiso(Integer id,String nombre,String descripcion,
            String estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    


   
    
    
    }