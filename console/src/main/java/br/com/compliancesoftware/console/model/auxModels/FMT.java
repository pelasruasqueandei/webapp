/**
 * 
 */
package br.com.compliancesoftware.console.model.auxModels;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Ajuda a formatar e parsear datas
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class FMT 
{
	public enum DateFormat {
		YMD("yyyy-MM-dd"),
		DMY("dd/MM/yyyy"),
		DMYHM("dd/MM/yyyy HH:mm"),
		EDMY("EEEE, dd 'de' MMMM 'de' yyyy");
		
		private String formato;
		
		private DateFormat(String formato) {
			this.formato = formato;
		}
		
		@Override
		public String toString() {
			return this.formato;
		}
	}
	
	/**
	 * M�todo pra criar um Calendar a partir de uma String
	 * @param data
	 * @return
	 */
	public static Calendar getCalendarFromString(String data, DateFormat format)
	{
		try
		{
//			TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
			SimpleDateFormat fmt = new SimpleDateFormat(format.toString());
			Calendar date = Calendar.getInstance();
			date.setTime(fmt.parse(data));
			return date;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * M�todo para criar uma String de data a partir de um Calendar
	 * @param calendar
	 * @return
	 */
	public static String getStringFromCalendar(Calendar calendar, DateFormat format)
	{
		try
		{
//			TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
			SimpleDateFormat fmt = new SimpleDateFormat(format.toString());
			String date = fmt.format(calendar.getTime());
			return date;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Pega a data de hoje como uma String no formato como o exemplo "Sexta-feira, 02 de Dezembro de 2016".
	 * @return
	 */
	public static String getHojeAsString(DateFormat format)
	{
		try
		{
//			TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
			Calendar hoje = Calendar.getInstance();
			hoje.setTimeInMillis(System.currentTimeMillis());
			SimpleDateFormat fmt = new SimpleDateFormat(format.toString());
			String date = fmt.format(hoje.getTime());
			return date;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Retorna um Calendar com a data de hoje, as horas, minutos, segundos e milisegundos estar�o zerados para que haja uma melhor compara��o a n�vel de data.
	 * @return
	 */
	public static Calendar getHoje()
	{
		try
		{
//			TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
			Calendar hoje = Calendar.getInstance();
			hoje.setTimeInMillis(System.currentTimeMillis());
			String zerada = getStringFromCalendar(hoje, DateFormat.DMY);
			hoje = getCalendarFromString(zerada, DateFormat.DMY);
			return hoje;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Retorna um calendar com o exato momento.
	 * @return
	 */
	public static Calendar getAgora() {
		try {
//			TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
			Calendar hoje = Calendar.getInstance();
			hoje.setTimeInMillis(System.currentTimeMillis());
			return hoje;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public enum NumberFormat {
		PXXPXSXXXXBXXXX("(XX)X XXXX-XXXX"),
		PXXPXXXXBXXXX("(XX)XXXX-XXXX");
		
		private String formato;
		
		private NumberFormat(String formato) {
			this.formato = formato;
		}
		
		public String toString() {
			return this.formato;
		}
		
	}
	
	/**
	 * Remove a formata��o de um determinado n�mero j� formatado.
	 * @param number
	 * @return
	 */
	public static String removeFormat(String number) {
		return number.replace("(", "").replace(")", "").replace("-", "").replace(" ", "").trim();
	}
	
	/**
	 * Formata uma String com sequencia num�rica com a m�scara passada por par�metro.
	 * @param number
	 * @param format
	 * @return
	 */
	public static String formatNumber(String number, NumberFormat format) throws Exception{
		for(char c : number.toCharArray()) {
			if(!Character.isDigit(c)) {
				throw new Exception("Wrong String: may have only numbers!");
			}
		}
		String formato = format.toString();
		
		String compare = removeFormat(formato);
		if(compare.length() != number.length())
			throw new Exception("Wrong String: lenght out of bounds!");
		
		int index = 0;
		String result = "";
		for(char c : formato.toCharArray()) {
			if(c == 'X') {
				result += number.charAt(index);
				index++;
			}
			else {
				result += c;
			}
		}
		
		return result;
	}
}
