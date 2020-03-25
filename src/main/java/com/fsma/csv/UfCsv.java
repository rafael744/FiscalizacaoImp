package com.fsma.csv;

public class UfCsv {
	
	private String nome;
	private String sigla;
	
	public UfCsv(String linha) {
		String campos[] = linha.split(", ");
		nome=campos[0];
		sigla=campos[1];
	}

	public String getNome() {
		return nome.toUpperCase();
	}
	
	public String getSigla() {
		return sigla.toUpperCase().trim();
	}
}
