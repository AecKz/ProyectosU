package com.aeckz.tiendavirtual.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the proveedor database table.
 * 
 */
@Entity
@NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p")
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String direccionid;

	private String email;

	private String nombre;

	private String telefono;

	// bi-directional many-to-one association to Producto
	@OneToMany(mappedBy = "proveedor")
	private List<Producto> productos;

	public Proveedor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDireccionid() {
		return this.direccionid;
	}

	public void setDireccionid(String direccionid) {
		this.direccionid = direccionid;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setProveedor(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setProveedor(null);

		return producto;
	}

}