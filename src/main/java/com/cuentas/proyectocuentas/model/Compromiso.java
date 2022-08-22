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

@Entity
@Table(name="compromiso")

public class Compromiso {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCom;

    @Column(name="numeroFac", length=50)
	@NotEmpty
	private String numeroFac;

	@Column(name="nombreEm", length=30)
	@NotEmpty
	private String nombreEm; 
    
	@Column(name="fecha")
	@NotEmpty
	private String fecha;

	@Column(name="fechaS")
	@NotEmpty
	private String fechaS;

	@Column(name="metodo", length=20)
	@NotEmpty
	private String metodo;

    @Column(name="total")
	@NotEmpty
	private String total;


	
	private boolean estadoCom;

	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	


	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Compromiso(Usuario usuario) {
		this.usuario = usuario;
	}
    @ManyToOne(fetch = FetchType.LAZY)
	private TipoCompromiso tipocompromiso;
	


	
	public TipoCompromiso getTipoCompromiso() {
		return tipocompromiso;
	}

	public void setTipoCompromiso(TipoCompromiso tipocompromiso) {
		this.tipocompromiso = tipocompromiso;
	}

	public Compromiso(TipoCompromiso tipocompromiso) {
		this.tipocompromiso = tipocompromiso;
	}
    public Compromiso() {
		
	}

    public Compromiso(Integer idCom, @NotEmpty String numeroFac, @NotEmpty String nombreEm, @NotEmpty String fecha,
            @NotEmpty String fechaS, @NotEmpty String metodo, @NotEmpty String total, boolean estadoCom) {
        this.idCom = idCom;
        this.numeroFac = numeroFac;
        this.nombreEm = nombreEm;
        this.fecha = fecha;
        this.fechaS = fechaS;
        this.metodo = metodo;
        this.total = total;
        this.estadoCom = estadoCom;
    }

    public Integer getIdCom() {
        return idCom;
    }

    public void setIdCom(Integer idCom) {
        this.idCom = idCom;
    }

    public String getNumeroFac() {
        return numeroFac;
    }

    public void setNumeroFac(String numeroFac) {
        this.numeroFac = numeroFac;
    }

    public String getNombreEm() {
        return nombreEm;
    }

    public void setNombreEm(String nombreEm) {
        this.nombreEm = nombreEm;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaS() {
        return fechaS;
    }

    public void setFechaS(String fechaS) {
        this.fechaS = fechaS;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public boolean isEstadoCom() {
        return estadoCom;
    }

    public void setEstadoCom(boolean estadoCom) {
        this.estadoCom = estadoCom;
    }

   

    
	

	
    
}
