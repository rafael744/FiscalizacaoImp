package com.fsma.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.fsma.entity.Empresa;
import com.fsma.entity.Municipio;
import com.fsma.entity.Uf;

public class EmpresaDao {

	EntityManager em;
	
	public EmpresaDao(EntityManager em) {
		this.em = em;
	}

	public void insere(Empresa empresa) {
		em.persist(empresa);
		
	}
	
	public Empresa buscaPorCnpj(String cnpj) {
		String jpqlEmpresa = "select e from Empresa e where e.cnpf = :pCnpj";
		TypedQuery<Empresa> queryEmpresa = em.createQuery(jpqlEmpresa, Empresa.class);
		
		queryEmpresa.setParameter("pCnpj", cnpj);
		
		try {
			return queryEmpresa.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}

	}

	
}
