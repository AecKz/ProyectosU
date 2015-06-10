package com.aeckz.tiendavirtual.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.aeckz.tiendavirtual.entitymanagerfactory.EntityManagerFactoryDAO;
import com.aeckz.tiendavirtual.model.Producto;

public class ProductoDAO extends EntityManagerFactoryDAO {
	public Producto crear(Producto objeto) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(objeto);
			em.flush();
			em.getTransaction().commit();

			return objeto;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return objeto;
		} finally {
			em.close();
		}
	}

	public Producto editar(Producto objeto) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(objeto);
			em.getTransaction().commit();
			return objeto;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			em.getTransaction().rollback();
			return objeto;
		} finally {
			em.close();
		}
	}

	public Producto eliminar(Producto objeto) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Producto ProductoToBeRemoved = em.getReference(Producto.class,
					objeto.getId());
			em.remove(ProductoToBeRemoved);
			em.getTransaction().commit();
			return objeto;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return objeto;
		} finally {
			em.close();
		}
	}

	public List<Producto> buscarTodos() {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			TypedQuery<Producto> query = em.createQuery(
					"SELECT p FROM Producto p order by p.nombre", Producto.class);
			List<Producto> results = query.getResultList();
			return results;
		} finally {
			em.close();
		}
	}

	public Producto buscarPorId(String id) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		Producto producto = new Producto();
		try {
			TypedQuery<Producto> query = em.createQuery(
					"SELECT p FROM Producto p where p.id = :id ", Producto.class)
					.setParameter("id", Integer.parseInt(id));
			List<Producto> results = query.getResultList();
			producto = results.get(0);
			return producto;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return producto;
		} finally {
			em.close();
		}
	}
}
