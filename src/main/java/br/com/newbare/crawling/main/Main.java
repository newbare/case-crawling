package br.com.newbare.crawling.main;

import br.com.newbare.crawling.service.ColetorDeArquivo;
import br.com.newbare.crawling.service.ColetorDeEmail;
import br.com.newbare.crawling.service.ColetorWeb;
import br.com.newbare.crawling.service.IColetor;
import br.com.newbare.crawling.util.Util;

public class Main {

	public static void main(final String[] args) {
		IColetor coletor;
		if (Util.isPossuiArgumentos(args) || Util.isPossuiComandosValidos(args)) {
			mostrarManualDeUso();
		}else{
			if (args[0].startsWith("-f") && args.length >= 2) {			// File
				coletor = new ColetorDeArquivo(args[1]);
			} else if (args[0].startsWith("-w") && args.length >= 3) {	// Web
				coletor = new ColetorWeb(args[1], args[2]);
			} else if (args[0].startsWith("-g") && args.length >= 3) {	// Gmail
				coletor = new ColetorDeEmail(args[1], args[2]);
			} else {
				mostrarManualDeUso();
			}
		}	
		
	}
	
	private static void mostrarManualDeUso() {
		try {
			Util.printTxt(Main.class.getResource("/usage.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
}
