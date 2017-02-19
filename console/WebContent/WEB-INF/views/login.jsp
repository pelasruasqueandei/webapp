<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>Console - Login</title>

<mtag:cssJsHead/>

</head>
<body background="<c:url value="resources/images/main_backimage_large.png" />">
<div class="container-fluid">
  <br>
  <br>
  <div class="row">
    <div class="col-md-4 col-md-offset-4">
      <div class="panel">
      	<div class="panel-heading center-block">
            <img id="logoimage" class="center-block" src="<c:url value="/resources/images/logo/Logo-300x100.png" />" alt="Console Logo">
			<script type="text/javascript">
				$(document).ready(function(){
					function resize(){
						var height = 58;
						var width = 300;
						
						var windowwidth = $(window).width();
						if(windowwidth < 350){
							width = 250;
						}
						
						$('#logoimage').height(height);
						$('#logoimage').width(width);
					}
					resize();
					
					$( window ).resize(function() {
						resize();
					});
				});
			</script>
        </div>
        <div class="panel-body">
        	<form class="form-group" method="post" action="entrar">
                <mtag:mensagem msg="${mensagem}"/>
            	<input name="nome" type="text" class="form-control" placeholder="Login ou E-mail" required autofocus>
                <input name="senha" type="password" class="form-control" placeholder="Senha" required>
                <button class="btn-block btn-white form-control info" type="submit">Entrar</button>
            </form>
            <mtag:modalbox bodymessage="Enviar senha por e-mail?" titlemessage="Esqueceu sua senha?" footeractiontext="Sim" linktext="Esqueci minha senha" footeractionhref="esqueciSenha" id="m1"/>
        </div>
      </div>
    </div>
  </div>
  <mtag:footerHtml/>
</div>

<mtag:cssJsFoot/>

</body>
</html>
