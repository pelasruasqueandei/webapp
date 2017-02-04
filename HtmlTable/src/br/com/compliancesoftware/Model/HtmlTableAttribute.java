/**
 * 
 */
package br.com.compliancesoftware.Model;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
/**
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 * Esta anotação é utilizada para marcar os atributos do objeto que seram mostrados como cabeçalho.
 * A ordem do cabeçalho é guiada pela ordem no momento da declaração dos atributos.
 */
public @interface HtmlTableAttribute 
{

}
