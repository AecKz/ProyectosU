package com.aeckz.tiendavirtual.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.aeckz.tiendavirtual.entitymanagerfactory.EntityManagerFactoryDAO;
import com.aeckz.tiendavirtual.model.Pedido;

public class PedidoDAO extends EntityManagerFactoryDAO {
	public Pedido crear(Pedido objeto) {
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

	public Pedido editar(Pedido objeto) {
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

	public Pedido eliminar(Pedido objeto) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Pedido PedidoToBeRemoved = em.getReference(Pedido.class,
					objeto.getId());
			em.remove(PedidoToBeRemoved);
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

	public List<Pedido> buscarTodos() {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			TypedQuery<Pedido> query = em.createQuery(
					"SELECT p FROM Pedido p order by p.fechaingreso", Pedido.class);
			List<Pedido> results = query.getResultList();
			return results;
		} finally {
			em.close();
		}
	}

	public Pedido buscarPorId(String id) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		Pedido pedido = new Pedido();
		try {
			TypedQuery<Pedido> query = em.createQuery(
					"SELECT p FROM Pedido p where p.id = :id ", Pedido.class)
					.setParameter("id", Integer.parseInt(id));
			List<Pedido> results = query.getResultList();
			pedido = results.get(0);
			return pedido;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return pedido;
		} finally {
			em.close();
		}
	}
	//Lista Carrito de Compras
	public List<Pedido> buscarActivosUsuario (String usuarioId) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			TypedQuery<Pedido> query = em.createQuery(
					"SELECT p FROM Pedido p where p.usuario.id = :id", Pedido.class)
					.setParameter("id", Integer.parseInt(usuarioId));
			List<Pedido> results = query.getResultList();			
			return results;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return null;
	}
}
