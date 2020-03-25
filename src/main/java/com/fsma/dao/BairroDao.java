package com.fsma.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.fsma.entity.Bairro;
import com.fsma.entity.Municipio;

public class BairroDao {
	
	private EntityManager em;
	
	public BairroDao(EntityManager em) {
		this.em = em;
	}

	public void insere(Bairro bairro) {
		em.persist(bairro);
	}

	public Bairro buscaPorNome(String bairroNome, Municipio municipio) {

		String jpqlBairro = "select b from Bairro b where b.municipio = :pMunicipio and b.nome = :pBairroNome";
		TypedQuery<Bairro> queryBairro = em.createQuery(jpqlBairro, Bairro.class);
		queryBairro.setParameter("pBairroNome",bairroNome);
		queryBairro.setParameter("pMunicipio", municipio);
		
		try {
			return queryBairro.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
