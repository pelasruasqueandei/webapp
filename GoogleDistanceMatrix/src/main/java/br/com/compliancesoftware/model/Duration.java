/**
 * 
 */
package br.com.compliancesoftware.model;

/**
 * Representa a duração dentro de um determinado percurso definido na pesquisa.
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class Duration 
{
	private String text;
	private String value;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
