package br.com.compliancesoftware.console.control.Controller;

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
import br.com.compliancesoftware.console.control.dao.AlertasDao;
import br.com.compliancesoftware.console.control.dao.LogsDao;
import br.com.compliancesoftware.console.control.dao.PerfilDao;
import br.com.compliancesoftware.console.model.Log;
import br.com.compliancesoftware.console.model.Perfil;

/**
 * Controller de logs do sistema.
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
@Transactional
@Controller
public class LogsController {
	@Qualifier("alertasJPA")
	@Autowired
	private AlertasDao alertasDao;
	
	@Qualifier("perfilJPA")
	@Autowired
	private PerfilDao perfilDao;
	
	@Qualifier("logsJPA")
	@Autowired
	private LogsDao logsDao;
	
	private static String mensagem = null;
	private List<Log> listaLogs;
	
	@RequestMapping("listaLogs")
	public String listaLogs(Model model, HttpSession session) {
		try{
			int qtdAlertas = alertasDao.conta();
			model.addAttribute("qtdAlertas", qtdAlertas);
			
			if(mensagem != null) {
				model.addAttribute("mensagem", mensagem);
				mensagem = null;
			}
			
			Perfil usuario = (Perfil)session.getAttribute("usuario");
			model.addAttribute("usuario",usuario);
			
			listaLogs = logsDao.lista(null, null);
			ArrayList<Log> beanList = new ArrayList<Log>();
			beanList.addAll(listaLogs);
			
			HtmlTableBuilder builder = new HtmlTableBuilder(null, null, "Tabela de logs do sistema", "Logs do Sistema", beanList);
			Params params = new Params();
			params.setFormatoDeData(DateFormat.DMYHM);
			builder.setParams(params);
			if(beanList != null && beanList.size() > 0)
				model.addAttribute("listaLogs",builder.getTable());
			
			return "logs/listaLogs";
		}
		catch(Exception e){
			e.printStackTrace();
			return "erro/banco";
		}
	}
	
	private boolean ordemCrescente = true;
	/**
	 * Método que reordena a tabela de acordo com a coluna clicada.
	 * @param field
	 * @param response
	 */
	@RequestMapping("getLogTableOrderBy")
	public void reordenaTabela(String field, HttpServletResponse response) {
		ArrayList<Log> beanList = new ArrayList<Log>();
		beanList.addAll(listaLogs);
		
		HtmlTableBuilder builder = new HtmlTableBuilder(null, null, "Tabela de logs do sistema", "Logs do Sistema", beanList);
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
