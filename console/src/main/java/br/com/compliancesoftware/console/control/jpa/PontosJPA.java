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
 * Implementação do acesso ao banco de dados para modificação da tabela de pontos turísticos
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
			return Mensagem.getOk("Ponto turístico cadastrado.");
		}
		else
			return Mensagem.getErro("Erro ao tentar cadastrar ponto turístico (nulo)");
	}

	@Override
	public String atualiza(PontoTuristico ponto) {
		if(ponto != null){
			manager.merge(ponto);
			return Mensagem.getOk("Ponto turístico atualizado.");
		}
		else
			return Mensagem.getErro("Erro ao atualizar ponto turístico (nulo)");
	}

	/* (non-Javadoc)
	 * @see br.com.compliancesoftware.console.control.dao.PontosDao#remove(java.lang.Long)
	 */
	@Override
	public String remove(Long id) {
		try{
			PontoTuristico ponto = getPorId(id);
			manager.remove(ponto);
			return Mensagem.getOk("Ponto removido com êxito.");
		}
		catch(Exception e){
			e.printStackTrace();
			return Mensagem.getErro("Erro ao tentar remover o Ponto Turístico.(falha interna)");
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
