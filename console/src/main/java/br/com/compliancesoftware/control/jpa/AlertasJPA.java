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
	
	@Override
	public String notifica(Alerta alerta) 
	{
		manager.persist(alerta);
		return Mensagem.getOk("Notificação criada.");
	}

	private Alerta getAlertaById(long id) {
		Alerta alerta = manager.find(Alerta.class, id);
		return alerta;
	}
	
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Alerta> lista() 
	{
		String q = "select a from Alerta as a";
		Query query = manager.createQuery(q);
		return query.getResultList();
	}
	
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

	@Override
	public void primeiroUso() 
	{
		List<Alerta> lista = lista();
		if(lista == null || lista.size()<1)
		{
			Alerta primeiro = new Alerta();
			primeiro.setMensagem(Mensagem.getInfo("Bem vindo ao seu gerenciador de pontos turísticos!"));
			primeiro.setData(FMT.getHoje());
			primeiro.setVisto(false);
			
			manager.persist(primeiro);
		}
	}
}
