package br.com.tutorial.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class BaseDAO<T> {
		
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("roteirizadorPersistence");
	
	private EntityManager em = emf.createEntityManager();
    	
	private String connecting;
	
	public void save(T obj) {
		try{					
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();		
			em.flush();
		}catch (Exception ex){
			System.out.println("ERRO : " + ex.toString());
		}
	}

	public void update(T obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
	}

	public void delete(T obj) {
		obj = em.merge(obj);
		em.remove(obj);
	}
				
	public String getConnecting() {
		return connecting;
	}

	public void setConnecting(String connecting) {
		this.connecting = connecting;
	}
		
	protected List<T> findByNome(String nome, Class<T> clazz) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
		Root<T> root = query.from(clazz);
		query.select(root).where(criteriaBuilder.equal(root.get("nome"), nome));
		return em.createQuery(query).getResultList();
	}
	
	protected List<T> findAll(Class<T> clazz) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
		Root<T> root = query.from(clazz);
		query.select(root);
		return em.createQuery(query).getResultList();
	}
	
	protected EntityManager getEm(){
		return em;
	}
}