package com.fsma.csv;

public class MunicipioCsv {
	
	private String nome;
	private String sigla;
	
	public MunicipioCsv(String linha) {
		String campos[] = linha.split(":");
		nome = campos[1];
		sigla = campos[0];
	}

	public String getNome() {
		return nome.toUpperCase();
	}
	
	public String getSigla() {
		return sigla.toUpperCase().trim();
	}
}
