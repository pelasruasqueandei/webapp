package br.com.compliancesoftware.control.dao;

import java.util.HashMap;

import br.com.compliancesoftware.model.Perfil;

/**
 * Indica como manipular os dados de perfis no banco de dados.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public interface PerfilDao 
{
	public Perfil getPerfilPorId(long id);
	public Perfil getPerfilPorNome(String nome);
	public Perfil getPerfilPorEmail(String email);
	public String alteraPerfil(Perfil perfil);
	public HashMap<String,Object> login(Perfil batendo);//Recebe um HashMap com os atributos Mensagem e Perfil do resultado do perfil que bate na porta
	public void primeiroUso();
}
