package br.com.compliancesoftware.console.control.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compliancesoftware.console.control.dao.AlertasDao;
import br.com.compliancesoftware.console.control.dao.LogsDao;
import br.com.compliancesoftware.console.control.dao.PerfilDao;
import br.com.compliancesoftware.console.control.dao.PontosDao;
import br.com.compliancesoftware.console.model.Log;
import br.com.compliancesoftware.console.model.Perfil;
import br.com.compliancesoftware.console.model.PontoTuristico;
import br.com.compliancesoftware.console.model.auxModels.EnviaEmail;
import br.com.compliancesoftware.console.model.auxModels.FMT;
import br.com.compliancesoftware.console.model.auxModels.Mensagem;

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
	
	@Qualifier("pontosJPA")
	@Autowired
	private PontosDao pontosDao;
	
	@Qualifier("logsJPA")
	@Autowired
	private LogsDao logsDao;
	
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
			
			List<PontoTuristico> lista = pontosDao.lista();
			
			if(lista != null && lista.size() > 0){
				String script = "  <script type=\"text/javascript\" charset=\"utf-8\">                                                                                                           "+
						"	var centro = {lat: -8.063206513853977, lng: -34.87147623345186};                                                                                             "+
						"	                                                                                                                                                             "+
						"	function initMap() {                                                                                                                                         "+
						"		map = new google.maps.Map(document.getElementById('map2'), {                                                                                             "+
						"			center: centro,                                                                                                                                      "+
						"			zoom: 13                                                                                                                                             "+
						"		});                                                                                                                                                      "+
						"		                                                                                                                                                         ";
						
						for(PontoTuristico ponto : lista){
							long id = ponto.getId();
							String nome = ponto.getNome();
							String latitude = ponto.getLatitude();
							String longitude = ponto.getLongitude();
							String atualizacao = ponto.getFmtAtualizacao();
							
							script += "		var marker"+id+" = new google.maps.Marker({                                                                                                                  "+
									"			position: {lat: "+latitude+", lng: "+longitude+"},                                                                                                           "+
									"			map: map,                                                                                                                                            "+
									"			draggable: false                                                                                                                                     "+
									"		});                        "+
									"                                                                                                                              "+
									"		marker"+id+".addListener('click', function() {                                                                                                                 "+
									"			var infowindow"+id+" = new google.maps.InfoWindow({                                                                                                      "+
									"				content: '<div><h4>"+nome+"</h4><br>Atualizado em: "+atualizacao+"<br><a href=\"atualizarPonto?id="+id+"\">Atualizar</a><br><a href=\"#\" data-toggle=\"modal\" data-target=\"#myModal"+id+"\">Remover</a></div>'								"+
									"			});                                                                                                                                                  "+
									"			infowindow"+id+".open(map, marker"+id+");                                                                                                              "+
									"		});";
						}
				
						script += "	}"+
						"  </script>                                                                                                                                                     "+
						"  <script async defer src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyCNgo7zFt-FiFjZtuPzhuDz7VUu87kIFhU&callback=initMap\" charset=\"utf-8\"></script>  ";
						for(PontoTuristico ponto : lista){
							long id = ponto.getId();
							script += "                <div id=\"myModal"+id+"\" class=\"modal fade\" role=\"dialog\">                            "+
							"   	<div class=\"modal-dialog\">                                                                                "+
							"   		<div class=\"modal-content\">                                                                           "+
							"   			<div class=\"modal-header\">                                                                        "+
							"   				<button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>                 "+
							"   				<h4 class=\"modal-title\">Tem certeza?</h4>                                                     "+
							"   			</div>                                                                                              "+
							"   			<div class=\"modal-body\">                                                                          "+
							"   				<p>Deseja realmente remover este Ponto Turístico?</p>                                                 "+
							"   			</div>                                                                                              "+
							"   			<div class=\"modal-footer\">                                                                        "+
							"   				<a href=\"removerPonto?id="+id+"\"><button class=\"btn btn-info\">Sim</button></a>   "+
							"   			</div>                                                                                              "+
							"   		</div>                                                                                                  "+
							"   	</div>                                                                                                      "+
							"</div>"+
							"                                                                                                            ";
						}
				model.addAttribute("script",script);
			}
			
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
	
	@RequestMapping("esqueciSenha")
	public String esqueciSenha(){
		Perfil perfil = perfilDao.getPrimeiroPerfil();
		if(perfil != null){
			String email = perfil.getEmail();
			
			String range = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$";
			String senha = "$";
			
			for(int i = 0;i < 11;i++){
				int index = (int)(Math.random()*12);
				String elemento = ""+range.charAt(index);
				senha += elemento;
			}
			
			perfil.setSenha(senha);
			perfilDao.alteraPerfil(perfil);
			
			String msg = EnviaEmail.envia(email, "[Pelas Ruas Que Andei] Troca de senha.", "Sua nova senha de acesso ao sistema é: "+senha);
			LoginController.setMsg(msg);
			
			if(Mensagem.contemOk(msg)){
				Log log = new Log();
				log.setAcao("Troca de senha pelo link");
				log.setData(FMT.getAgora());
				logsDao.adiciona(log);
			}
			
			return "redirect:login";
		}
		else{
			LoginController.setMsg(Mensagem.getErro("E-mail não enviado, contate o T.I."));
			return "redirect:login";
		}
	}
	
}
