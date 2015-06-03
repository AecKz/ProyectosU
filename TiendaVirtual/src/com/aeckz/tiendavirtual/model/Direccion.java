package com.aeckz.tiendavirtual.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the direccion database table.
 * 
 */
@Entity
@NamedQuery(name = "Direccion.findAll", query = "SELECT d FROM Direccion d")
public class Direccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	private String referencia;

	// bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumn(name = "ciudadid")
	private Ciudad ciudad;

	// bi-directional many-to-one association to Sector
	@ManyToOne
	@JoinColumn(name = "sectorid")
	private Sector sector;

	// bi-directional many-to-one association to Entidad
	@OneToMany(mappedBy = "direccion")
	private List<Entidad> entidads;

	public Direccion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Sector getSector() {
		return this.sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public List<Entidad> getEntidads() {
		return this.entidads;
	}

	public void setEntidads(List<Entidad> entidads) {
		this.entidads = entidads;
	}

	public Entidad addEntidad(Entidad entidad) {
		getEntidads().add(entidad);
		entidad.setDireccion(this);

		return entidad;
	}

	public Entidad removeEntidad(Entidad entidad) {
		getEntidads().remove(entidad);
		entidad.setDireccion(null);

		return entidad;
	}

}