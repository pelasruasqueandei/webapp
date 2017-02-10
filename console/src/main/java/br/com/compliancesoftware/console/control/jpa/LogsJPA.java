package br.com.compliancesoftware.console.control.jpa;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.compliancesoftware.console.control.dao.LogsDao;
import br.com.compliancesoftware.console.model.Log;

/**
 * Implementação do DAO de Logs para JPA
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Repository
public class LogsJPA implements LogsDao
{
	@PersistenceContext
	EntityManager manager;
	
	/**
	 * Cria um novo log no banco de dados.
	 */
	@Override
	public void adiciona(Log log) 
	{
		manager.persist(log);
	}

	/**
	 * Lista todos os logs do sistema com base na data de inicio e fim (caso nulas, não limita)
	 */
	@Override
	public List<Log> lista(Calendar inicio, Calendar fim) 
	{
		Date dt_i;
		Date dt_f;
		
		String q = "select l from Log as l";
		Query query;
		if(inicio != null && fim != null)
		{
			dt_i = new Date(inicio.getTimeInMillis());
			dt_f = new Date(fim.getTimeInMillis());
			q += " where l.data >= :paramInicio and l.data <= :paramFim";
			query = manager.createQuery(q);
			query.setParameter("paramInicio", dt_i);
			query.setParameter("paramFim", dt_f);
		}
		else if(inicio == null && fim != null)
		{
			dt_f = new Date(fim.getTimeInMillis());
			q += " where l.data <= :paramFim";
			query = manager.createQuery(q);
			query.setParameter("paramFim", dt_f);
		}
		else if(inicio != null && fim == null)
		{
			dt_i = new Date(inicio.getTimeInMillis());
			q += " where l.data >= :paramInicio";
			query = manager.createQuery(q);
			query.setParameter("paramInicio", dt_i);
		}
		else
		{
			query = manager.createQuery(q);
		}
		@SuppressWarnings("unchecked")
		List<Log> lista = query.getResultList();
		
		return lista;
	}
}
