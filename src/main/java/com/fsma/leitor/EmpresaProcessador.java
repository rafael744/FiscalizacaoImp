package com.fsma.leitor;

import javax.persistence.EntityManager;

import com.fsma.csv.FiscalizacaoCsv;
import com.fsma.dao.BairroDao;
import com.fsma.dao.EmpresaDao;
import com.fsma.dao.MunicipioDao;
import com.fsma.dao.UfDao;
import com.fsma.entity.Bairro;
import com.fsma.entity.Empresa;
import com.fsma.entity.Municipio;
import com.fsma.entity.Uf;

public class EmpresaProcessador implements Processador {

	private EntityManager em;
	private UfDao ufDao;
	private MunicipioDao municipioDao;
	private BairroDao bairroDao; 
	private EmpresaDao empresaDao;

	public EmpresaProcessador(EntityManager em) {
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
			if(municipio == null) {
				em.getTransaction().rollback();
				return;
			}
			
			// Recupera Bairro
			Bairro bairro = bairroDao.buscaPorNome(fiscalizacaoCsv.getBairro(), municipio);
			if(bairro == null) {
				em.getTransaction().rollback();
				return;
			}		
			
			Empresa empresa = empresaDao.buscaPorCnpj(fiscalizacaoCsv.getCnpj());
			if(empresa==null) {
				empresa = new Empresa();
				empresa.setRazaoSocial(fiscalizacaoCsv.getRazaoSocial());
				empresa.setCnpj(fiscalizacaoCsv.getCnpj());
				empresa.setCep(fiscalizacaoCsv.getCep());
				empresa.setUf(uf);
				empresa.setMunicipio(municipio);
				empresa.setBairro(bairro);
				empresa.setLogradouro(fiscalizacaoCsv.getLogradouro());
			}
			
			em.getTransaction().commit();
		} catch(Exception e ) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.exit(0);
		}
	}
	
}

