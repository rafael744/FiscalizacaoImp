package com.fsma.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.fsma.entity.Uf;

public class UfDao {
	
	private EntityManager em;
	
	public UfDao(EntityManager em) {
		this.em = em;
	}

	public Uf buscaPorSigla(String sigla) {

		String jpql = "Select u from Uf u where u.sigla = :pSigla";
		TypedQuery<Uf> query = em.createQuery(jpql, Uf.class);
		query.setParameter("pSigla",sigla);
		
		try {
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}

	}

	public Uf buscaPorNome(String nome) {

		String jpql = "Select u from Uf u where u.nome = :pNome";
		TypedQuery<Uf> query = em.createQuery(jpql, Uf.class);
		query.setParameter("pNome",nome);
		
		try {
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}

	}
}
