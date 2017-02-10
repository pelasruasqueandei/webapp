package br.com.compliancesoftware.console.control.dao;

import java.util.List;

import br.com.compliancesoftware.console.model.Alerta;

/**
 * Gerenciador de informações na tabela alertas no banco de dados.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public interface AlertasDao 
{
	public String notifica(Alerta alerta);
	public String modificaVisibilidade(long id);
	public List<Alerta> lista();
	public int conta();
	public void primeiroUso();
}
