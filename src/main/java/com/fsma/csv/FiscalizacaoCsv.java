package com.fsma.csv;

public class FiscalizacaoCsv {
	
	private String anoMes;
	private String cnpj;
	private String razaoSocial;
	private String logradouro;
	private String cep;
	private String bairro;
	private String municipio;
	private String ufNome;
	private boolean valido;
	
	public FiscalizacaoCsv(String linha) {
		String campos[] = linha.split(";");
		if (campos.length != 9) {
			valido = false;
			return;
		}
		valido = true;
		anoMes = campos[1];
		cnpj = campos[2];
		razaoSocial = campos[3];
		logradouro = campos[4];
		cep = campos[5];
		bairro = campos[6];
		municipio = campos[7];
		ufNome = campos[8];
	}

	public boolean isValido() {
		return valido;
	}

	public String getCep() {
		return cep;
	}

	public String getBairro() {
		return bairro.toUpperCase();
	}

	public String getMunicipio() {
		return municipio.toUpperCase();
	}

	public String getUfNome() {
		return ufNome.toUpperCase();
	}

	public String getAnoMes() {
		return anoMes;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial.toUpperCase();
	}

	public String getLogradouro() {
		return logradouro.toUpperCase();
	}

}
