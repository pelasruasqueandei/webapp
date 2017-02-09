package br.com.compliancesoftware.control.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compliancesoftware.control.dao.AlertasDao;
import br.com.compliancesoftware.control.dao.PerfilDao;
import br.com.compliancesoftware.model.Perfil;

/**
 * Controlador de requisições do site(projeto).
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Transactional
@Controller
public class SystemController
{
	@Qualifier("alertasJPA")
	@Autowired
	private AlertasDao alertasDao;
	
	@Qualifier("perfilJPA")
	@Autowired
	private PerfilDao perfilDao;
	
	private static String mensagem = null;
	
	public static void setMsg(String msg) {
		mensagem = msg;
	}
	
	/**
	 * Retorna à página principal.
	 * @param model
	 * @return
	 */
	@RequestMapping("home")
	public String home(Model model, HttpSession session)
	{
		try{
			int qtdAlertas = alertasDao.conta();
			model.addAttribute("qtdAlertas", qtdAlertas);
			
			if(mensagem != null) {
				model.addAttribute("mensagem", mensagem);
				mensagem = null;
			}
			
			Perfil usuario = (Perfil)session.getAttribute("usuario");
			model.addAttribute("usuario",usuario);
			
			return "index";
		}
		catch(Exception e){
			e.printStackTrace();
			return "erro/banco";
		}
	}
	
	/**
	 * Retorna à página principal.
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String home2()
	{
		return "redirect:home";
	}
}
