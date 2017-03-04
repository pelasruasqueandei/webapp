package main;

import java.util.Calendar;

import br.com.compliancesoftware.console.model.auxModels.FMT;
import br.com.compliancesoftware.console.model.auxModels.FMT.DateFormat;
import br.com.compliancesoftware.console.model.auxModels.Hunter;

/**
 * expõe saidas no console do eclipse para testes visuais.
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String frase = "Texto bastante grande para ser encriptado e mostrado em seu tamanho encriptado!";
		System.out.println("Frase: "+frase+"\nTamanho: "+frase.length());
		
		String cripted = encripta(frase);
		System.out.println("\nFrase encriptada: "+cripted+"\nTamanho: "+cripted.length());
	}

	private static String encripta(String frase){
		String hunt = Hunter.hunt(frase);
		return hunt;
	}
	
	/**
	 * Imprime a data de hoje, no formato desejado, no console
	 */
	private static void imprimeHojeComoString(){
		String data = FMT.getHojeAsString(DateFormat.DMYHM);
		System.out.println(data);
	}
	
	/**
	 * Método que converte uma String em Calendar
	 */
	private static void converteData(){
		String data = "02/12/2017 12:15";
		Calendar cData = FMT.getCalendarFromString(data, DateFormat.DMYHM);
		System.out.println(cData.toString());
	}
	
}
