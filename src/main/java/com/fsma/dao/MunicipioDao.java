package com.fsma.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.fsma.entity.Municipio;
import com.fsma.entity.Uf;

public class MunicipioDao {
	
	private EntityManager em;
	
	public MunicipioDao(EntityManager em) {
		this.em = em;
	}

	public void insere(Municipio municipio) {
		em.persist(municipio);
	}

	public Municipio buscaPorNome(String nome, Uf uf) {
		String jpqlMunicipio = "select m from Municipio m where m.uf = :pUf and m.nome = :pMunicipioNome";
		TypedQuery<Municipio> queryMunicipio = em.createQuery(jpqlMunicipio, Municipio.class);
		
		queryMunicipio.setParameter("pMunicipioNome", nome);
		queryMunicipio.setParameter("pUf", uf);
		
		try {
			return queryMunicipio.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
}
