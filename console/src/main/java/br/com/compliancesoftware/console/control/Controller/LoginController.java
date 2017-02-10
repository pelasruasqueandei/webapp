package br.com.compliancesoftware.console.control.Controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compliancesoftware.console.control.dao.AlertasDao;
import br.com.compliancesoftware.console.control.dao.LogsDao;
import br.com.compliancesoftware.console.control.dao.PerfilDao;
import br.com.compliancesoftware.console.model.Log;
import br.com.compliancesoftware.console.model.Perfil;
import br.com.compliancesoftware.console.model.auxModels.Mensagem;

/**
 * Controlador das páginas de login
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Transactional
@Controller
public class LoginController 
{
	private static String mensagem="";
	
	@Qualifier("perfilJPA")
	@Autowired
	private PerfilDao perfilDao;
	
	@Qualifier("alertasJPA")
	@Autowired
	private AlertasDao alertasDao;
	
	@Qualifier("logsJPA")
	@Autowired
	private LogsDao logsDao;
	
	public static void setMsg(String msg)
	{
		mensagem = msg;
	}
	
	/**
	 * Função de efetuar o login de perfil.
	 * @param perfil
	 * @param session
	 * @return
	 */
	@RequestMapping("entrar")
	public String efetuaLogin(String nome, String senha, HttpSession session)
	{
		try{
			Perfil perfil = new Perfil();
			perfil.setSenha(senha);
			perfil.setNome(nome);
			
			HashMap<String,Object> result = perfilDao.login(perfil);
			perfil = (Perfil)result.get("Perfil");
			mensagem = (String)result.get("Mensagem");
			
			if(Mensagem.contemOk(mensagem)) {
				session.setAttribute("usuario", perfil);
				
				Log log = new Log();
				log.setAcao(mensagem);
				log.setData(null);
				logsDao.adiciona(log);
				
				return "redirect:home";
			}
			else {
				session.invalidate();
				perfil = null;
				return "redirect:login";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "erro/banco";
		}
	}
	
	/**
	 * Efetua logout
	 * @param session
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session)
	{
		try{
			session.invalidate();
			mensagem = Mensagem.getInfo("Você saiu do sistema.");
			
			Log log = new Log();
			log.setAcao(mensagem);
			log.setData(null);
			logsDao.adiciona(log);
			
			return "redirect:login";
		}catch(Exception e){
			e.printStackTrace();
			return "erro/banco";
		}
	}
	
	/**
	 * Direciona usuário para a tela de login.
	 * @return
	 */
	@RequestMapping("login")
	public String login(Model model, HttpSession session)
	{
		try{
			alertasDao.primeiroUso();
			perfilDao.primeiroUso();
			
			if(mensagem != null) {
				model.addAttribute("mensagem",mensagem);
				mensagem = null;
			}
			
			return "login";
		}catch(Exception e){
			e.printStackTrace();
			return "erro/banco";
		}
	}
	
}
