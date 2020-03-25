package com.fsma.main;

import javax.persistence.EntityManager;

import com.fsma.leitor.ArqMunicipioProcessador;
import com.fsma.leitor.LeitorArquivo;
import com.fsma.leitor.ListaFiscalizacaoProcessador;
import com.fsma.leitor.UfProcessador;

public class Principal {

	private static String arquivoUf = "C:\\Users\\T-Gamer\\Desktop\\AC\\estados.csv";
	//private static String arquivoMunicipio = "C:\\Users\\T-Gamer\\Desktop\\AC\\municipiosEsse.csv";
	private static String arquivoEmpresa = "C:\\Users\\T-Gamer\\Desktop\\AC\\FiscalizaSaoPauloCsv.csv";

	public static void main(String args[]) {
		System.out.println("Iniciando importação");
		EntityManager em = JPAUtil.getEntityManager();
		LeitorArquivo leitor = new LeitorArquivo();
		leitor.executa(arquivoUf, new UfProcessador(em));
		//leitor.executa(arquivoMunicipio, new ArqMunicipioProcessador(em));
		leitor.executa(arquivoEmpresa, new ListaFiscalizacaoProcessador(em));
		em.close();
		//leitor.verificaMunicipiosEmpresa(arquivoEmpresa);
		System.out.println("Fim da importação");
		System.exit(0);


	}
}
