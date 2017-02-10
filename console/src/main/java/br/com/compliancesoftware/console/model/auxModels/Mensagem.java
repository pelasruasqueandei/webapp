/**
 * 
 */
package br.com.compliancesoftware.console.model.auxModels;

/**
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class Mensagem 
{
	private static final String OK = "<strong>Ok!</strong> ";
	private static final String ERRO = "<strong>Erro!</strong> ";
	private static final String INFO = "<strong>Info!</strong> ";
	
	public static String getOk(String mensagem) {
		return OK + mensagem;
	}
	
	public static String getErro(String mensagem) {
		return ERRO + mensagem;
	}
	
	public static String getInfo(String mensagem) {
		return INFO + mensagem;
	}
	
	public static boolean contemOk(String mensagem) {
		return mensagem.contains(OK);
	}
	
	public static boolean contemErro(String mensagem) {
		return mensagem.contains(ERRO);
	}
	
	public static boolean contemInfo(String mensagem) {
		return mensagem.contains(INFO);
	}
}
