package com.fsma.leitor;

import javax.persistence.EntityManager;

import com.fsma.csv.FiscalizacaoCsv;
import com.fsma.dao.MunicipioDao;
import com.fsma.dao.UfDao;
import com.fsma.entity.Municipio;
import com.fsma.entity.Uf;

public class MunicipioProcessador implements Processador {

	private EntityManager em;
	private UfDao ufDao;
	private MunicipioDao municipioDao;

	public MunicipioProcessador(EntityManager em) {
		this.em = em;
		this.ufDao = new UfDao(em);
		this.municipioDao = new MunicipioDao(em);
	}

	@Override
	public void processa(String linha) {
		try {
			em.getTransaction().begin();

			//Recupera Uf
			FiscalizacaoCsv fiscalizacaoCsv = new FiscalizacaoCsv(linha);
			Uf uf = ufDao.buscaPorNome(fiscalizacaoCsv.getUfNome());
			if(uf==null) {
				em.getTransaction().rollback();
				return;
			}
			// Recupera Município
			Municipio municipio = municipioDao.buscaPorNome(fiscalizacaoCsv.getMunicipio(), uf);
			
			// Insere Município, caso ele não exista
			if(municipio == null) {
				municipio = new Municipio();
				municipio.setNome(fiscalizacaoCsv.getMunicipio());
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

