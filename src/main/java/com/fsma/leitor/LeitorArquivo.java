package com.fsma.leitor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LeitorArquivo {

	public void executa(String arquivo, Processador processador) {

		try {
			InputStream inputStream = new FileInputStream(arquivo);
			InputStreamReader importStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader br = new BufferedReader(importStreamReader);
			String linha = br.readLine();

			while (linha != null) {
				processador.processa(linha);
				linha = br.readLine();
			}
			br.close();
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	// IMportar MUnicipio
	// Importar Bairro
	/*
	 * private void importa(FiscalizacaoCsv empresaCsv) {
	 * 
	 * EntityManager em = JPAUtil.getEntityManager(); em.getTransaction().begin();
	 * 
	 * // Recupera UF String jpql = "Select u from Uf u where u.nome = :pUfNome";
	 * TypedQuery<Uf> queryUf = em.createQuery(jpql, Uf.class);
	 * queryUf.setParameter("pUfNome", empresaCsv.getUfNome()); Uf uf; try { uf =
	 * queryUf.getSingleResult(); }catch (NoResultException e) { return; }
	 * 
	 * // Recupera Município String jpqlMunicipio =
	 * "select m from Municipio m where m.uf = :pUf and m.nome = :pMunicipioNome";
	 * TypedQuery<Municipio> queryMunicipio = em.createQuery(jpqlMunicipio,
	 * Municipio.class); queryMunicipio.setParameter("pMunicipioNome",
	 * empresaCsv.getMunicipio()); queryMunicipio.setParameter("pUf", uf);
	 * 
	 * Municipio municipio; try { municipio = queryMunicipio.getSingleResult();
	 * }catch (NoResultException e) { municipio = null; }
	 * 
	 * // Insere Município, caso ele não exista if(municipio == null) { municipio =
	 * new Municipio(); municipio.setNome(empresaCsv.getMunicipio());
	 * municipio.setUf(uf); em.persist(municipio); }
	 * 
	 * // Recupera Bairro String jpqlBairro =
	 * "select b from Bairro b where b.municipio = :pMunicipio and b.nome = :pBairroNome"
	 * ; TypedQuery<Bairro> queryBairro = em.createQuery(jpqlBairro, Bairro.class);
	 * queryBairro.setParameter("pBairroNome", empresaCsv.getBairro());
	 * queryBairro.setParameter("pMunicipio", municipio);
	 * 
	 * Bairro bairro; try { bairro = queryBairro.getSingleResult(); }catch
	 * (NoResultException e) { bairro = null; }
	 * 
	 * if(empresaCsv.getBairro()!="Sem Informação") { //Insere Bairro, caso ele não
	 * exista if(bairro == null && empresaCsv.getBairro().length()!=0) { bairro =
	 * new Bairro(); bairro.setNome(empresaCsv.getBairro());
	 * bairro.setMunicipio(municipio); em.persist(bairro); } }
	 * em.getTransaction().commit(); em.close();
	 * 
	 * }
	 */
	
}

