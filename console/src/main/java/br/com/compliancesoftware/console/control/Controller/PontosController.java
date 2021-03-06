package br.com.compliancesoftware.console.control.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import br.com.compliancesoftware.console.control.dao.AlertasDao;
import br.com.compliancesoftware.console.control.dao.LogsDao;
import br.com.compliancesoftware.console.control.dao.PerfilDao;
import br.com.compliancesoftware.console.control.dao.PontosDao;
import br.com.compliancesoftware.console.model.Log;
import br.com.compliancesoftware.console.model.Perfil;
import br.com.compliancesoftware.console.model.PontoTuristico;
import br.com.compliancesoftware.console.model.auxModels.FMT;
import br.com.compliancesoftware.console.model.auxModels.Mensagem;

/**
 * Controlador de views e requisi��es de pontos tur�sticos
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
	
	@Qualifier("pontosJPA")
	@Autowired
	private PontosDao pontosDao;
	
	/**
	 * Abre p�gina de cadastramentro de ponto tur�stico
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
	 * Cadastra um novo ponto tur�stico no mapa.
	 * @param ponto
	 * @param request
	 * @return
	 */
	@RequestMapping("cadastraPonto")
	public String cadastraPonto(PontoTuristico ponto, Model model, HttpServletRequest request){
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			MultipartFile imagem = multipartRequest.getFile("image");
			byte[] conteudo = imagem.getBytes();
			if(imagem != null && conteudo != null && conteudo.length > 1)
				ponto.setFoto(conteudo);
			else
				ponto.setFoto();
			
			mensagem = pontosDao.adiciona(ponto);
			SystemController.setMsg(mensagem);
			
			if(Mensagem.contemOk(mensagem)){
				Log log = new Log();
				log.setAcao(mensagem);
				log.setData(FMT.getAgora());
				logsDao.adiciona(log);
			}
			
			mensagem = null;
			return "redirect:home";
			
		}catch(Exception e){
			e.printStackTrace();
			return "erro/banco";
		}
	}
	
	/**
	 * Abre p�gina de atualiza��o de ponto tur�stico
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("atualizarPonto")
	public String atualizarPonto(Long id, Model model, HttpSession session) {
		try{
			int qtdAlertas = alertasDao.conta();
			model.addAttribute("qtdAlertas", qtdAlertas);
			
			if(mensagem != null) {
				model.addAttribute("mensagem", mensagem);
				mensagem = null;
			}
			
			Perfil usuario = (Perfil)session.getAttribute("usuario");
			model.addAttribute("usuario",usuario);
			
			PontoTuristico ponto = pontosDao.getPorId(id);
			model.addAttribute("ponto",ponto);
			
			return "pontos/atualizar";
		}catch(Exception e){
			e.printStackTrace();
			return "erro/banco";
		}
	}
	
	/**
	 * Atualiza um ponto tur�stico no mapa.
	 * @param ponto
	 * @param request
	 * @return
	 */
	@RequestMapping("atualizaPonto")
	public String atualizaPonto(PontoTuristico ponto, Model model, HttpServletRequest request){
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			MultipartFile imagem = multipartRequest.getFile("image");
			byte[] conteudo = imagem.getBytes();
			if(imagem != null && conteudo != null && conteudo.length > 1)
				ponto.setFoto(conteudo);
			else
				ponto.setFoto();
			
			mensagem = pontosDao.atualiza(ponto);
			SystemController.setMsg(mensagem);
			
			if(Mensagem.contemOk(mensagem)){
				Log log = new Log();
				log.setAcao(mensagem);
				log.setData(FMT.getAgora());
				logsDao.adiciona(log);
			}
			
			mensagem = null;
			return "redirect:home";
			
		}catch(Exception e){
			e.printStackTrace();
			return "erro/banco";
		}
	}
	
	/**
	 * Remove um ponto do mapa
	 * @param id
	 * @return
	 */
	@RequestMapping("removerPonto")
	public String removerPonto(Long id){
		try{
			mensagem = pontosDao.remove(id);
			SystemController.setMsg(mensagem);
			
			if(Mensagem.contemOk(mensagem)){
				Log log = new Log();
				log.setAcao(mensagem);
				log.setData(FMT.getAgora());
				logsDao.adiciona(log);
			}
			
			mensagem = null;
			return "redirect:home";
		}
		catch(Exception e){
			e.printStackTrace();
			return "erro/banco";
		}
	}
	
	/**
	 * Passa a imagem do ponto em quest�o atrav�s de stream da response.
	 * @param id
	 * @param response
	 */
	@RequestMapping("imagemPonto")
	public void mostraFoto(Long id, HttpServletResponse response){
		try{
			PontoTuristico ponto = pontosDao.getPorId(id);
			byte[] foto = ponto.getFoto();
			if(foto != null)
			{
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(foto);
				response.getOutputStream().close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna um JSon com os pontos cadastrados no banco
	 * @param response
	 */
	@RequestMapping(value="getPontos", method=RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public void getPontos(String ret, HttpServletResponse response){
		try{
			List<PontoTuristico> lista = pontosDao.lista();
			if(lista != null && lista.size() > 0){
				ArrayList<PontoTuristico> listaPontos = new ArrayList<PontoTuristico>();
				listaPontos.addAll(lista);
				XStream xstream = null;
				if(ret != null && ret.equals("json"))
					xstream = new XStream(new JettisonMappedXmlDriver());
				else
					xstream = new XStream();
				
				xstream.setMode(XStream.NO_REFERENCES);
				xstream.alias("PontoTuristico", PontoTuristico.class);
				String xml = xstream.toXML(listaPontos);
				
				response.setCharacterEncoding("UTF-8");
				
				response.getWriter().print(xml);
				response.getWriter().close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
