package main;

import java.util.Calendar;

import br.com.compliancesoftware.console.model.auxModels.FMT;
import br.com.compliancesoftware.console.model.auxModels.FMT.DateFormat;

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
		imprimeHojeComoString();
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
