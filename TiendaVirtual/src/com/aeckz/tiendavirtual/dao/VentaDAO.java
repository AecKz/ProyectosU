package com.aeckz.tiendavirtual.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.aeckz.tiendavirtual.entitymanagerfactory.EntityManagerFactoryDAO;
import com.aeckz.tiendavirtual.model.Venta;

public class VentaDAO extends EntityManagerFactoryDAO {
	public Venta crear(Venta objeto) {
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

	public Venta editar(Venta objeto) {
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

	public Venta eliminar(Venta objeto) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Venta VentaToBeRemoved = em.getReference(Venta.class,
					objeto.getId());
			em.remove(VentaToBeRemoved);
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

	public List<Venta> buscarTodos() {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			TypedQuery<Venta> query = em.createQuery(
					"SELECT e FROM Venta e order by e.nombre", Venta.class);
			List<Venta> results = query.getResultList();
			return results;
		} finally {
			em.close();
		}
	}

	public Venta buscarPorId(String id) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		Venta venta = new Venta();
		try {
			TypedQuery<Venta> query = em.createQuery(
					"SELECT c FROM Venta c where c.id = :id ", Venta.class)
					.setParameter("id", id);
			List<Venta> results = query.getResultList();
			venta = results.get(0);
			return venta;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return venta;
		} finally {
			em.close();
		}
	}
}
