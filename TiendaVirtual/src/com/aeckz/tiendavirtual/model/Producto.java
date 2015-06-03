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
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	private String nombre;

	private double precioproveedor;

	private double preciopublico;

	private int stock;

	// bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy = "producto")
	private List<Pedido> pedidos;

	// bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name = "categoriaid")
	private Categoria categoria;

	// bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name = "proveedorid")
	private Proveedor proveedor;

	public Producto() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioproveedor() {
		return this.precioproveedor;
	}

	public void setPrecioproveedor(double precioproveedor) {
		this.precioproveedor = precioproveedor;
	}

	public double getPreciopublico() {
		return this.preciopublico;
	}

	public void setPreciopublico(double preciopublico) {
		this.preciopublico = preciopublico;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido addPedido(Pedido pedido) {
		getPedidos().add(pedido);
		pedido.setProducto(this);

		return pedido;
	}

	public Pedido removePedido(Pedido pedido) {
		getPedidos().remove(pedido);
		pedido.setProducto(null);

		return pedido;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}