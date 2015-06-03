package com.aeckz.tiendavirtual.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the envio database table.
 * 
 */
@Entity
@NamedQuery(name = "Envio.findAll", query = "SELECT e FROM Envio e")
public class Envio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaentrega;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechasalida;

	private String observaciones;

	// bi-directional many-to-one association to Venta
	@ManyToOne
	@JoinColumn(name = "ventaid")
	private Venta venta;

	public Envio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaentrega() {
		return this.fechaentrega;
	}

	public void setFechaentrega(Date fechaentrega) {
		this.fechaentrega = fechaentrega;
	}

	public Date getFechasalida() {
		return this.fechasalida;
	}

	public void setFechasalida(Date fechasalida) {
		this.fechasalida = fechasalida;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

}