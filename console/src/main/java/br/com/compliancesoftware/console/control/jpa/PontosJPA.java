package br.com.compliancesoftware.console.control.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.compliancesoftware.console.control.dao.PontosDao;
import br.com.compliancesoftware.console.model.PontoTuristico;
import br.com.compliancesoftware.console.model.auxModels.Mensagem;

/**
 * Implementa��o do acesso ao banco de dados para modifica��o da tabela de pontos tur�sticos
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
@Repository
public class PontosJPA implements PontosDao{
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public PontoTuristico getPorId(Long id) {
		return manager.find(PontoTuristico.class, id);
	}

	@Override
	public String adiciona(PontoTuristico ponto) {
		if(ponto != null){
			manager.persist(ponto);
			return Mensagem.getOk("Ponto tur�stico cadastrado.");
		}
		else
			return Mensagem.getErro("Erro ao tentar cadastrar ponto tur�stico (nulo)");
	}

	@Override
	public String atualiza(PontoTuristico ponto) {
		if(ponto != null){
			manager.merge(ponto);
			return Mensagem.getOk("Ponto tur�stico atualizado.");
		}
		else
			return Mensagem.getErro("Erro ao atualizar ponto tur�stico (nulo)");
	}

	/* (non-Javadoc)
	 * @see br.com.compliancesoftware.console.control.dao.PontosDao#remove(java.lang.Long)
	 */
	@Override
	public String remove(Long id) {
		try{
			PontoTuristico ponto = getPorId(id);
			manager.remove(ponto);
			return Mensagem.getOk("Ponto removido com �xito.");
		}
		catch(Exception e){
			e.printStackTrace();
			return Mensagem.getErro("Erro ao tentar remover o Ponto Tur�stico.(falha interna)");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PontoTuristico> lista() {
		Query q = manager.createQuery("select pt from PontoTuristico as pt");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PontoTuristico> listaMonumentos() {
		Query q = manager.createQuery("select pt from PontoTuristico as pt where pt.tipo = :tipo");
		q.setParameter("tipo", "Monumento");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PontoTuristico> listaLocais() {
		Query q = manager.createQuery("select pt from PontoTuristico as pt where pt.tipo = :tipo");
		q.setParameter("tipo", "Local");
		return q.getResultList();
	}

}
