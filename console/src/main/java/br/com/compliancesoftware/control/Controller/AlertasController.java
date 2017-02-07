package br.com.compliancesoftware.control.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compliancesoftware.Model.HtmlTableBuilder;
import br.com.compliancesoftware.Model.Params;
import br.com.compliancesoftware.Model.Params.DateFormat;
import br.com.compliancesoftware.control.dao.AlertasDao;
import br.com.compliancesoftware.control.dao.LogsDao;
import br.com.compliancesoftware.model.Alerta;
import br.com.compliancesoftware.model.Perfil;

/**
 * Controller para alertas do sistema.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Transactional
@Controller
public class AlertasController 
{
	@Qualifier("alertasJPA")
	@Autowired
	private AlertasDao alertasDao;
	
	@Qualifier("logsJPA")
	@Autowired
	private LogsDao logsDao;
	
	private static String mensagem = null;
	
	private List<Alerta> listaAlertas;
	
	/**
	 * Lista todos os alertas do sistema.
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("listaAlertas")
	public String listaAlertas(Model model, HttpSession session) {
		Perfil usuario = (Perfil)session.getAttribute("usuario");
		
		int qtdAlertas = alertasDao.conta();
		listaAlertas = alertasDao.lista();
		ArrayList<Alerta> beanList = new ArrayList<Alerta>();
		beanList.addAll(listaAlertas);
		
		model.addAttribute("qtdAlertas",qtdAlertas);
		model.addAttribute("usuario",usuario);
		
		HtmlTableBuilder builder = new HtmlTableBuilder("center-align", null, "Tabela de alertas do sistema.", "Alertas do Sistema", beanList);
		builder.setUpdateAction(true);
		Params params = new Params();
		params.setFormatoDeData(DateFormat.DMYHM);
		builder.setParams(params);
		if(beanList != null && beanList.size() > 0)
			model.addAttribute("listaAlertas", builder.getTableOrderBy("1", "0"));
		
		if(mensagem != null) {
			model.addAttribute("mensagem",mensagem);
			mensagem = null;
		}
		
		return "alertas/listaAlertas";
	}
	
	/**
	 * Modifica a visibilidade de um determinado alerta.
	 * @param id = id do alerta a modificar.
	 * @param response = resposta dada à requisição que deve ser usada via Ajax.
	 */
	@RequestMapping("atualizarAlerta")
	public void atualizarAlerta(Long id, HttpServletResponse response) {
		mensagem = alertasDao.modificaVisibilidade(id);
		System.out.println(mensagem);
		
		listaAlertas = alertasDao.lista();
		ArrayList<Alerta> beanList = new ArrayList<Alerta>();
		beanList.addAll(listaAlertas);
		
		HtmlTableBuilder builder = new HtmlTableBuilder("center-align", null, "Tabela de alertas do sistema.", "Alertas do Sistema", beanList);
		builder.setUpdateAction(true);
		Params params = new Params();
		params.setFormatoDeData(DateFormat.DMYHM);
		builder.setParams(params);
		if(beanList != null && beanList.size() > 0)
			builder.writeTable(response, builder.getTableOrderBy("1", "0"));
	}
	
	private boolean ordemCrescente = true;
	/**
	 * Ordena a tabela de acordo com a coluna clicada.
	 * @param field
	 * @param response
	 */
	@RequestMapping("getAlertaTableOrderBy")
	public void reordenaTabela(String field, HttpServletResponse response) {
		ArrayList<Alerta> beanList = new ArrayList<Alerta>();
		beanList.addAll(listaAlertas);
		
		HtmlTableBuilder builder = new HtmlTableBuilder("center-align", null, "Tabela de alertas do sistema.", "Alertas do Sistema", beanList);
		builder.setUpdateAction(true);
		Params params = new Params();
		params.setFormatoDeData(DateFormat.DMYHM);
		builder.setParams(params);
		if(beanList != null && beanList.size() > 0){
			ordemCrescente = !ordemCrescente;
			String ordem = "0";
			if(ordemCrescente)
				ordem = "0";
			else
				ordem = "1";
			builder.writeTable(response, builder.getTableOrderBy(field, ordem));
		}
	}
	
}
