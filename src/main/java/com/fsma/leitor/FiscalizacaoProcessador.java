package com.fsma.leitor;

import javax.persistence.EntityManager;

import com.fsma.csv.FiscalizacaoCsv;
import com.fsma.dao.BairroDao;
import com.fsma.dao.MunicipioDao;
import com.fsma.dao.UfDao;
import com.fsma.entity.Bairro;
import com.fsma.entity.Municipio;
import com.fsma.entity.Uf;

public class FiscalizacaoProcessador implements Processador {

	private EntityManager em;
	private UfDao ufDao;
	private MunicipioDao municipioDao;
	private BairroDao bairroDao; 

	public FiscalizacaoProcessador(EntityManager em) {
		this.em = em;
		this.ufDao = new UfDao(em);
		this.municipioDao = new MunicipioDao(em);
		this.bairroDao = new BairroDao(em);
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
			
			// Recupera Bairro
			Bairro bairro = bairroDao.buscaPorNome(fiscalizacaoCsv.getBairro(), municipio);
					
			//Insere Bairro, caso ele não exista
			if(bairro == null)  {
				if((fiscalizacaoCsv.getBairro() != "SEM INFORMAÇÃO") && (fiscalizacaoCsv.getBairro().length() != 0 )) {
					bairro = new Bairro();
					bairro.setNome(fiscalizacaoCsv.getBairro());
					bairro.setMunicipio(municipio);
					bairroDao.insere(bairro);
				} 
			}
			
			// Recupera Empresa
			
			//Empresa empresa = empresaDao.buscaPorCnpj()
			
			//
			
			
			em.getTransaction().commit();
		} catch(Exception e ) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.exit(0);
		}
	}
	
}

