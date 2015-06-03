package com.aeckz.tiendavirtual.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.aeckz.tiendavirtual.entitymanagerfactory.EntityManagerFactoryDAO;
import com.aeckz.tiendavirtual.model.Usuario;

public class UsuarioDAO extends EntityManagerFactoryDAO {
	public Usuario crear(Usuario objeto) {
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

	public Usuario editar(Usuario objeto) {
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

	public Usuario eliminar(Usuario objeto) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Usuario UsuarioToBeRemoved = em.getReference(Usuario.class,
					objeto.getId());
			em.remove(UsuarioToBeRemoved);
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

	public List<Usuario> buscarTodos() {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			TypedQuery<Usuario> query = em.createQuery(
					"SELECT u FROM Usuario u", Usuario.class);
			List<Usuario> results = query.getResultList();
			return results;
		} finally {
			em.close();
		}
	}

	public Usuario buscarPorId(String id) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		Usuario usuario = new Usuario();
		try {
			TypedQuery<Usuario> query = em.createQuery(
					"SELECT u FROM Usuario u where u.id = :id ", Usuario.class)
					.setParameter("id", id);
			List<Usuario> results = query.getResultList();
			usuario = results.get(0);
			return usuario;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return usuario;
		} finally {
			em.close();
		}
	}

	public String login(String login, String clave) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		String resultado = "0";
		try {
			TypedQuery<Usuario> query = em.createQuery(
					"SELECT u FROM Usuario u where u.login = :login and u.clave = :clave", Usuario.class)
					.setParameter("login", login).setParameter("clave", clave);
			List<Usuario> results = query.getResultList();
			if(results.size()>0){
				resultado = "1";				
			}
			return resultado;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return resultado;
		} finally {
			em.close();
		}
	}
}
