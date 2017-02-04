/**
 * 
 */
package br.com.compliancesoftware.Model;

/**
 * Parametros que podem ser usados durante a construção de tabelas.
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class Params {
	private DateFormat formatoDeData;
	
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

	public DateFormat getFormatoDeData() {
		return formatoDeData;
	}

	public void setFormatoDeData(DateFormat formatoDeData) {
		this.formatoDeData = formatoDeData;
	}
	
	
}
