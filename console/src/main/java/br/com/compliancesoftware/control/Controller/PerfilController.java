package br.com.compliancesoftware.control.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import br.com.compliancesoftware.control.dao.AlertasDao;
import br.com.compliancesoftware.control.dao.LogsDao;
import br.com.compliancesoftware.control.dao.PerfilDao;
import br.com.compliancesoftware.model.Log;
import br.com.compliancesoftware.model.Perfil;
import br.com.compliancesoftware.model.auxModels.Mensagem;

/**
 * Controller criado para organizar o método de atualização do perfil.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Transactional
@Controller
public class PerfilController 
{
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
	 * Abre página de gerenciamento de perfil
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("gerenciarPerfil")
	public String gerenciarPerfil(Model model, HttpSession session) {
		int qtdAlertas = alertasDao.conta();
		model.addAttribute("qtdAlertas", qtdAlertas);
		
		if(mensagem != null) {
			model.addAttribute("mensagem", mensagem);
			mensagem = null;
		}
		
		Perfil usuario = (Perfil)session.getAttribute("usuario");
		model.addAttribute("usuario",usuario);
		
		return "perfil/gerenciarPerfil";
	}
	
	/**
	 * Atualiza o perfil.
	 * @return
	 */
	@RequestMapping("atualizaPerfil")
	public String atualiza(Perfil perfil, HttpSession session, HttpServletRequest request) throws Exception
	{
		Perfil onDB = perfilDao.getPerfilPorId(perfil.getId());
		perfil.setFoto(onDB.getFoto());
		perfil.setAcesso(onDB.getAcesso());
		perfil.setUltAcesso(onDB.getUltAcesso());
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("image");
		
		byte[] conteudo = multipartFile.getBytes();
		
		if(multipartFile != null && conteudo != null && conteudo.length > 1)
		{
			try
			{
				byte[] image = multipartFile.getBytes();
				
				perfil.setFoto(image);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		mensagem = perfilDao.alteraPerfil(perfil);
		if(Mensagem.contemOk(mensagem))
		{
			Log log = new Log();
			log.setAcao(mensagem);
			log.setData(null);
			logsDao.adiciona(log);
		}
		session.setAttribute("usuario", perfil);
		
		return "redirect:gerenciarPerfil";
	}
	
	/**
	 * Auxilia na recuperação de imagens do banco de dados.
	 */
	@RequestMapping("mostraFoto")
	public void mostraFoto(Long id, HttpServletResponse response) throws Exception
	{
		Perfil perfil = perfilDao.getPerfilPorId(id);
		byte[] foto = perfil.getFoto();
		
		if(foto != null)
		{
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(foto);
			response.getOutputStream().close();
		}
	}
}
