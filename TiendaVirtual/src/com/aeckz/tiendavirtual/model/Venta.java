package com.aeckz.tiendavirtual.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the venta database table.
 * 
 */
@Entity
@NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaactualiza;

	private String formapago;

	private double totalenvio;

	private double totalimpuestos;

	private double totalpedido;

	private double totalventa;

	// bi-directional many-to-one association to Envio
	@OneToMany(mappedBy = "venta")
	private List<Envio> envios;

	// bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name = "pedidoid")
	private Pedido pedido;

	public Venta() {
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

	public Date getFechaactualiza() {
		return this.fechaactualiza;
	}

	public void setFechaactualiza(Date fechaactualiza) {
		this.fechaactualiza = fechaactualiza;
	}

	public String getFormapago() {
		return this.formapago;
	}

	public void setFormapago(String formapago) {
		this.formapago = formapago;
	}

	public double getTotalenvio() {
		return this.totalenvio;
	}

	public void setTotalenvio(double totalenvio) {
		this.totalenvio = totalenvio;
	}

	public double getTotalimpuestos() {
		return this.totalimpuestos;
	}

	public void setTotalimpuestos(double totalimpuestos) {
		this.totalimpuestos = totalimpuestos;
	}

	public double getTotalpedido() {
		return this.totalpedido;
	}

	public void setTotalpedido(double totalpedido) {
		this.totalpedido = totalpedido;
	}

	public double getTotalventa() {
		return this.totalventa;
	}

	public void setTotalventa(double totalventa) {
		this.totalventa = totalventa;
	}

	public List<Envio> getEnvios() {
		return this.envios;
	}

	public void setEnvios(List<Envio> envios) {
		this.envios = envios;
	}

	public Envio addEnvio(Envio envio) {
		getEnvios().add(envio);
		envio.setVenta(this);

		return envio;
	}

	public Envio removeEnvio(Envio envio) {
		getEnvios().remove(envio);
		envio.setVenta(null);

		return envio;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}