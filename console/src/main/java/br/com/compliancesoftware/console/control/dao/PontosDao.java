package br.com.compliancesoftware.console.control.dao;

import java.util.List;

import br.com.compliancesoftware.console.model.PontoTuristico;

/**
 * Interface de acesso ao banco de dados.
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public interface PontosDao {
	public PontoTuristico getPorId(Long id);
	public String adiciona(PontoTuristico ponto);
	public String atualiza(PontoTuristico ponto);
	public String remove(Long id);
	public List<PontoTuristico> lista();
	public List<PontoTuristico> listaMonumentos();
	public List<PontoTuristico> listaLocais();
}
