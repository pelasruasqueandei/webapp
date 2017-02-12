package br.com.compliancesoftware.console.control.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import br.com.compliancesoftware.console.control.dao.AlertasDao;
import br.com.compliancesoftware.console.control.dao.LogsDao;
import br.com.compliancesoftware.console.control.dao.PerfilDao;
import br.com.compliancesoftware.console.model.Perfil;
import br.com.compliancesoftware.console.model.PontoTuristico;

/**
 * Controlador de views e requisições de pontos turísticos
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
@Transactional
@Controller
public class PontosController {
private static String mensagem = null;
	
	@Qualifier("perfilJPA")
	@Autowired
	private PerfilDao perfilDao;
	
	@Qualifier("logsJPA")
	@Autowired
	private LogsDao logsDao;
	
	@Qualifier("alertasJPA")
	@Autowired
	private AlertasDao alertasDao;
	
	/**
	 * Abre página de cadastramentro de ponto turístico
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("cadastrarPonto")
	public String cadastrarPonto(Model model, HttpSession session) {
		try{
			int qtdAlertas = alertasDao.conta();
			model.addAttribute("qtdAlertas", qtdAlertas);
			
			if(mensagem != null) {
				model.addAttribute("mensagem", mensagem);
				mensagem = null;
			}
			
			Perfil usuario = (Perfil)session.getAttribute("usuario");
			model.addAttribute("usuario",usuario);
			
			return "pontos/cadastrar";
		}catch(Exception e){
			e.printStackTrace();
			return "erro/banco";
		}
	}
	
	/**
	 * Cadastra um novo ponto turístico no mapa.
	 * @param ponto
	 * @param request
	 * @return
	 */
	@RequestMapping("cadastraPonto")
	public String cadastraPonto(PontoTuristico ponto, HttpServletRequest request){
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			MultipartFile imagem = multipartRequest.getFile("image");
			byte[] conteudo = imagem.getBytes();
			if(imagem != null && conteudo != null && conteudo.length > 1)
				ponto.setFoto(conteudo);
			//else
				//ponto.setFoto();
			
			//TODO terminar esquema de persistencia de pontos turisticos
			return null;
			
		}catch(Exception e){
			e.printStackTrace();
			return "banco/erro";
		}
	}
	
}
