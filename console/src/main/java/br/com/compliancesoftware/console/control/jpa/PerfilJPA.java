package br.com.compliancesoftware.console.control.jpa;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.compliancesoftware.console.control.dao.PerfilDao;
import br.com.compliancesoftware.console.model.Perfil;
import br.com.compliancesoftware.console.model.auxModels.FMT;
import br.com.compliancesoftware.console.model.auxModels.Mensagem;
import br.com.compliancesoftware.console.model.auxModels.ValidaEmail;

/**
 * Acesso à tabela de Perfis do banco de dados.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Repository
public class PerfilJPA implements PerfilDao
{
	@PersistenceContext
	EntityManager manager;
	
	/**
	 * Recupera informações de um determinado perfil através do id dele.
	 */
	@Override
	public Perfil getPerfilPorId(long id) {
		Perfil perfil = manager.find(Perfil.class, id);
		return perfil;
	}

	/**
	 * Recupera informações de um determinado perfil através do nome dele.
	 */
	@Override
	public Perfil getPerfilPorNome(String nome) {
		Query query = manager.createQuery("select p from Perfil as p where p.nome = :paramNome");
		query.setParameter("paramNome", nome);
		@SuppressWarnings("unchecked")
		List<Perfil> lista = query.getResultList();
		if(lista != null && lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}
	
	/**
	 * Recupera informações de um determinado perfil através do e-mail dele.
	 */
	@Override
	public Perfil getPerfilPorEmail(String email) {
		Query query = manager.createQuery("select p from Perfil as p where p.email = :paramEmail");
		query.setParameter("paramEmail", email);
		@SuppressWarnings("unchecked")
		List<Perfil> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return lista.get(0);
		return null;
	}
	
	/**
	 * Método para atualizar um perfil.
	 */
	@Override
	public String alteraPerfil(Perfil perfil) {
		if(ValidaEmail.isValido(perfil.getEmail())) {
			manager.merge(perfil);
			return Mensagem.getOk("Perfil atualizado.");
		}
		else
			return Mensagem.getErro("E-mail inválido!");
	}

	/**
	 * Método para logar no sistema.
	 */
	@Override
	public HashMap<String, Object> login(Perfil batendo)
	{
		String nome = batendo.getNome();
		String senha = batendo.getSenha();
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		Perfil perfil = getPerfilPorNome(nome);
		
		if(perfil == null)
		{
			perfil = getPerfilPorEmail(nome);
			if(perfil == null)
			{
				result.put("Mensagem", Mensagem.getErro("Usuário não existe."));
				result.put("Perfil", perfil);
				
				return result;
			}
		}
		if(perfil.getSenha().equals(senha))
		{
			result.put("Mensagem", Mensagem.getOk("Bem vindo "+nome+"!"));
			result.put("Perfil", perfil);
			
			perfil.setUltAcesso(perfil.getAcesso());
			perfil.setAcesso(FMT.getAgora());
			
			manager.merge(perfil);
			
			return result;
		}
		result.put("Mensagem", Mensagem.getErro("Senha incorreta."));
		perfil = null;
		result.put("Perfil", perfil);
		
		return result;
	}
	
	/**
	 * Lista perfis cadastrados no sistema
	 */
	private List<Perfil> lista()
	{
		Query query = manager.createQuery("select p from Perfil as p");
		@SuppressWarnings("unchecked")
		List<Perfil> perfis = query.getResultList();
		if(perfis != null && perfis.size() > 0)
			return perfis;
		return null;
	}

	/**
	 * Parametriza o banco de dados caso seja seu primeiro uso
	 */
	@Override
	public void primeiroUso()
	
	{
		List<Perfil> lista = lista();
		if(lista == null || lista.size()<1)
		{
			Perfil adm = new Perfil();
			Calendar agora = FMT.getAgora();
			adm.setAcesso(agora);
			adm.setContato("81996729491");
			try {
				adm.setFoto();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			adm.setEmail("douglas.ceo@compliancesoftware.com.br");
			adm.setNome("Administrador");
			adm.setSenha("adm");
			adm.setUltAcesso(agora);
			
			manager.persist(adm);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.compliancesoftware.console.control.dao.PerfilDao#getPrimeiroPerfil()
	 */
	@Override
	public Perfil getPrimeiroPerfil() {
		List<Perfil> lista = lista();
		if(lista != null && lista.size() > 0){
			Perfil perfil = lista.get(0);
			return perfil;
		}
		else
			return null;
	}
	
}
