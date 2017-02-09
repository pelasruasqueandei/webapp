package br.com.compliancesoftware.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.compliancesoftware.model.Perfil;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter
{
	@Override
	  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object controller) throws Exception
	 {
		  Perfil usuario = (Perfil)request.getSession().getAttribute("usuario");
	      String uri = request.getRequestURI();
	      
	      if(uri.endsWith("login")||uri.endsWith("entrar")||uri.contains("resources")||uri.contains("tags/")||uri.contains("erro")) {
	    	  return true;
	      }

	      if(usuario != null) {
	    	  return true;
	      }
	      
	      System.out.println("Acesso negado...Redirecionando...");
	      response.sendRedirect("login");
	      return false;
	 }
}
