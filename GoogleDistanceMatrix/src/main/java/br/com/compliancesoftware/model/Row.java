/**
 * 
 */
package br.com.compliancesoftware.model;

/**
 * Representa uma linha do unmarsh.
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class Row 
{
	private Element element;
	
	public void setElement(Element element)
	{
		this.element = element;
	}
	
	public Element getElement()
	{
		return this.element;
	}
}
