package br.com.compliancesoftware.control.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compliancesoftware.Model.HtmlTableBuilder;
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
	
	@RequestMapping("listaAlertas")
	public String listaAlertas(Model model, HttpSession session) {
		Perfil usuario = (Perfil)session.getAttribute("usuario");
		
		int qtdAlertas = alertasDao.conta();
		List<Alerta> listaAlertas = alertasDao.lista();
		ArrayList<Alerta> beanList = new ArrayList<Alerta>();
		beanList.addAll(listaAlertas);
		
		model.addAttribute("qtdAlertas",qtdAlertas);
		model.addAttribute("usuario",usuario);
		
		HtmlTableBuilder builder = new HtmlTableBuilder("center-align", null, "Tabela de alertas do sistema.", "Alertas do Sistema", beanList);
		builder.setUpdateAction(true);
		if(beanList != null && beanList.size() > 0)
			model.addAttribute("listaAlertas", builder.getTableOrderBy("1", "0"));
		
		if(mensagem != null) {
			model.addAttribute("mensagem",mensagem);
			mensagem = null;
		}
		
		return "alertas/listaAlertas";
	}
	
	@RequestMapping("atualizarAlerta")
	public String atualizarAlerta(Long id, Model model, HttpSession session) {
		mensagem = alertasDao.modificaVisibilidade(id);
		
		Perfil usuario = (Perfil)session.getAttribute("usuario");
		
		int qtdAlertas = alertasDao.conta();
		List<Alerta> listaAlertas = alertasDao.lista();
		ArrayList<Alerta> beanList = new ArrayList<Alerta>();
		beanList.addAll(listaAlertas);
		
		model.addAttribute("qtdAlertas",qtdAlertas);
		model.addAttribute("usuario",usuario);
		
		HtmlTableBuilder builder = new HtmlTableBuilder("center-align", null, "Tabela de alertas do sistema.", "Alertas do Sistema", beanList);
		builder.setUpdateAction(true);
		if(beanList != null && beanList.size() > 0)
			model.addAttribute("listaAlertas", builder.getTableOrderBy("1", "0"));
		
		if(mensagem != null) {
			model.addAttribute("mensagem",mensagem);
			mensagem = null;
		}
		
		return "alertas/listaAlertas";
	}
	
}
