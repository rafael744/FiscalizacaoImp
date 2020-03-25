package com.fsma.leitor;

import javax.persistence.EntityManager;

import com.fsma.csv.UfCsv;
import com.fsma.entity.Uf;

public class UfProcessador implements Processador {

	private EntityManager em;

	public UfProcessador(EntityManager em) {
		this.em = em;
	}

	@Override
	public void processa(String linha) {
		try {
			em.getTransaction().begin();
			
			UfCsv ufCsv = new UfCsv(linha);
			Uf uf = new Uf();
			uf.setNome(ufCsv.getNome());
			uf.setSigla(ufCsv.getSigla());			
							
			em.persist(uf);
			
			em.getTransaction().commit();
		} catch(Exception e ) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.exit(0);
		}
	}
	
}
