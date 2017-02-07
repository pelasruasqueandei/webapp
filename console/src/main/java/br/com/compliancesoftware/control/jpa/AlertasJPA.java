package br.com.compliancesoftware.control.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.compliancesoftware.control.dao.AlertasDao;
import br.com.compliancesoftware.model.Alerta;
import br.com.compliancesoftware.model.auxModels.FMT;
import br.com.compliancesoftware.model.auxModels.Mensagem;

/**
 * Implementação do DAO de Alertas para JPA.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Repository
public class AlertasJPA implements AlertasDao
{
	@PersistenceContext
	EntityManager manager;
	
	/**
	 * Cria um alerta no banco de dados.
	 */
	@Override
	public String notifica(Alerta alerta) 
	{
		manager.persist(alerta);
		return Mensagem.getOk("Notificação criada.");
	}

	/**
	 * Recupera um alerta do banco de dados usando o campo id.
	 * @param id
	 * @return
	 */
	private Alerta getAlertaById(long id) {
		Alerta alerta = manager.find(Alerta.class, id);
		return alerta;
	}
	
	/**
	 * Modifica a informação de "foi visto" de um determinado Alarme através de seu id.
	 */
	@Override
	public String modificaVisibilidade(long id) 
	{
		Alerta alerta = getAlertaById(id);
		if(alerta != null) {
			alerta.setVisto(!alerta.getVisto());
			manager.merge(alerta);
			return Mensagem.getOk("Notificação atualizada!");
		}
		else
			return Mensagem.getErro("Notificação inexistente.");
	}

	/**
	 * Lista os alertas do banco de dados.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Alerta> lista() 
	{
		String q = "select a from Alerta as a";
		Query query = manager.createQuery(q);
		return query.getResultList();
	}
	
	/**
	 * Conta os alertas não vistos no sistema.
	 */
	@Override
	public int conta()
	{
		Query query = manager.createQuery("select a from Alerta as a where a.visto = :paramVisto");
		query.setParameter("paramVisto", false);
		@SuppressWarnings("unchecked")
		List<Alerta> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return lista.size();
		return 0;
	}

	/**
	 * Auxilia a criação de um primeiro alerta ao iniciar o sistema pela primeira vez
	 */
	@Override
	public void primeiroUso() 
	{
		List<Alerta> lista = lista();
		if(lista == null || lista.size()<1)
		{
			Alerta primeiro = new Alerta();
			primeiro.setMensagem(Mensagem.getInfo("Bem vindo ao seu gerenciador de pontos turísticos!"));
			primeiro.setData(FMT.getAgora());
			primeiro.setVisto(false);
			
			manager.persist(primeiro);
		}
	}
}
