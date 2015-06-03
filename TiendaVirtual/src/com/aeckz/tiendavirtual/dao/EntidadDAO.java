package com.aeckz.tiendavirtual.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.aeckz.tiendavirtual.entitymanagerfactory.EntityManagerFactoryDAO;
import com.aeckz.tiendavirtual.model.Entidad;

public class EntidadDAO extends EntityManagerFactoryDAO {
	public Entidad crear(Entidad objeto) {
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

	public Entidad editar(Entidad objeto) {
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

	public Entidad eliminar(Entidad objeto) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Entidad EntidadToBeRemoved = em.getReference(Entidad.class,
					objeto.getId());
			em.remove(EntidadToBeRemoved);
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

	public List<Entidad> buscarTodos() {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			TypedQuery<Entidad> query = em.createQuery(
					"SELECT e FROM Entidad e order by e.nombre", Entidad.class);
			List<Entidad> results = query.getResultList();
			return results;
		} finally {
			em.close();
		}
	}

	public Entidad buscarPorId(String id) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		Entidad entidad = new Entidad();
		try {
			TypedQuery<Entidad> query = em.createQuery(
					"SELECT c FROM Entidad c where c.id = :id ", Entidad.class)
					.setParameter("id", id);
			List<Entidad> results = query.getResultList();
			entidad = results.get(0);
			return entidad;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return entidad;
		} finally {
			em.close();
		}
	}

	public List<Entidad> buscarActivos() {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		List<Entidad> results = null;
		try {
			TypedQuery<Entidad> query = em.createQuery(
					"SELECT c FROM Entidad c WHERE c.activo =:valorActivo",
					Entidad.class).setParameter("valorActivo", true);
			results = query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return results;
	}

	public int buscarPorIdentificacion(String identificacion) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		int contador = 0;
		try {
			TypedQuery<Entidad> q = em
					.createQuery(
							"SELECT count(c) FROM Entidad c WHERE c.identificacion =:identificacion",
							Entidad.class).setParameter("identificacion",
							identificacion);
			contador = q.getResultList().size();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return contador;
	}

	public Entidad buscarEntidadPorIdentificacion(String identificacion) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		Entidad entidad = new Entidad();
		try {
			em.getTransaction().begin();
			TypedQuery<Entidad> query = em
					.createQuery(
							"SELECT c FROM Entidad c WHERE c.identificacion =:identificacion",
							Entidad.class).setParameter("identificacion",
							identificacion);
			int existe = query.getResultList().size();
			if (existe > 0) {
				List<Entidad> results = query.getResultList();
				entidad = results.get(0);
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return entidad;
		} finally {
			em.close();
		}
		return entidad;
	}

	public Entidad buscarEntidadPorMail(String mail) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		Entidad entidad = new Entidad();
		try {
			em.getTransaction().begin();
			TypedQuery<Entidad> query = em.createQuery(
					"SELECT c FROM Entidad c WHERE c.mail =:mail",
					Entidad.class).setParameter("mail", mail);
			int existe = query.getResultList().size();
			if (existe == 0) {
				entidad = null;
			} else {
				List<Entidad> results = query.getResultList();
				entidad = results.get(0);
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return entidad;
	}

	public Entidad buscarEntidadPorIdEnsurance(String idEnsurance) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		Entidad entidad = new Entidad();
		try {
			em.getTransaction().begin();
			TypedQuery<Entidad> query = em
					.createQuery(
							"SELECT c FROM Entidad c WHERE c.entEnsurance =:idEnsurance",
							Entidad.class).setParameter("idEnsurance",
							idEnsurance);
			int existe = query.getResultList().size();
			if (existe > 0) {
				List<Entidad> results = query.getResultList();
				entidad = results.get(0);
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return entidad;
		} finally {
			em.close();
		}
		return entidad;
	}

}
