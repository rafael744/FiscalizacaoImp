package com.fsma.leitor;

import javax.persistence.EntityManager;

import com.fsma.csv.MunicipioCsv;
import com.fsma.dao.MunicipioDao;
import com.fsma.dao.UfDao;
import com.fsma.entity.Municipio;
import com.fsma.entity.Uf;

public class ArqMunicipioProcessador implements Processador {

	private EntityManager em;
	private UfDao ufDao;
	private MunicipioDao municipioDao;

	public ArqMunicipioProcessador(EntityManager em) {
		this.em = em;
		this.ufDao = new UfDao(em);
		this.municipioDao = new MunicipioDao(em);
	}

	@Override
	public void processa(String linha) {
		try {
	    	em.getTransaction().begin();

	    	MunicipioCsv municipioCsv = new MunicipioCsv(linha);
	    	
	    	Uf uf = ufDao.buscaPorSigla(municipioCsv.getSigla());
	    	
	    	if (uf != null) {
		    	Municipio municipio = new Municipio();
				municipio.setNome(municipioCsv.getNome());
				municipio.setUf(uf);
				municipioDao.insere(municipio);
	    	}
			em.getTransaction().commit();
		
		} catch(Exception e ) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.exit(0);
		}
	}

}

