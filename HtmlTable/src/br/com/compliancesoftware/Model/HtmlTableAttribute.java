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
 * Esta anota��o � utilizada para marcar os atributos do objeto que seram mostrados como cabe�alho.
 * A ordem do cabe�alho � guiada pela ordem no momento da declara��o dos atributos.
 */
public @interface HtmlTableAttribute 
{

}
