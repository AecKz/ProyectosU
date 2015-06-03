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
 * The persistent class for the pedido database table.
 * 
 */
@Entity
@NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaactualiza;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaingreso;

	// bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name = "productoid")
	private Producto producto;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "usuarioid")
	private Usuario usuario;

	// bi-directional many-to-one association to Venta
	@OneToMany(mappedBy = "pedido")
	private List<Venta> ventas;

	public Pedido() {
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

	public Date getFechaingreso() {
		return this.fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setPedido(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setPedido(null);

		return venta;
	}

}