package com.fsma.leitor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class ListaFiscalizacaoProcessador implements Processador {

	private List<Processador> processadores = new ArrayList<>();

	public ListaFiscalizacaoProcessador(EntityManager em) {
		processadores.add(new MunicipioProcessador(em));
		processadores.add(new BairroProcessador(em));
//		processadores.add(new EmpresaProcessador(em));
//		processadores.add(new FiscalizacaoProcessador(em));
	}

	@Override
	public void processa(String linha) {
		for (Processador p : processadores) {
			p.processa(linha);
		}
	}

}
