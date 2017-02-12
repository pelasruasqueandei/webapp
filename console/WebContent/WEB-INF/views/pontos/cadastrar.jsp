<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>Console - Cadastramento Ponto Turístico</title>

<mtag:cssJsHead/>

</head>
<body id="pagina" background="<c:url value="resources/images/main_backimage_large.png" />">
<mtag:navbar qtdAlertas="${qtdAlertas}" uid="${usuario.id}" index="false"/>
<!-- body -->
<div id="corpo" class="container-fluid nav-padded">
  <div class="row">
	<div class="col-md-6 col-md-offset-3">
    	<div class="row">
        	<hr>
            <div id="form" class="panel panel-default nav-padded">
                <mtag:mensagem msg="${mensagem}"/>
            	<div class="panel-heading text-center">
                	<h3>Cadastro de novo ponto turístico</h3>
                </div>
            	<div class="panel-body">
                	<div class="row">
                        <form class="form-group" method="post" action="cadastraPonto" enctype="multipart/form-data">
                       	    <div class="col-md-6">
                           	    <div class="fileupload">
                                    <mtag:loginImage src=""/>
                                    <input type="file" id="foto" name="image">
                                </div>
                            </div>
                 		    <div class="col-md-6">
                        	    <br>
                            	<input name="nome" type="text" class="form-control" placeholder="Nome" required autofocus>
                            	<select name="tipo" class="form-control">
    								<option value = "Local">Local</option>
    								<option value = "Monumento">Monumento</option>
                                </select>
                                <input name="altitude" type="text" class="form-control" placeholder="Altitude" required readonly>
                            	<input name="latitude" type="text" class="form-control" placeholder="Latitude" required readonly>
                            	<input name="endereco" type="text" class="form-control" placeholder="Endereço" required>
                                <textarea rows="5" cols="5" class="form-control" placeholder="História" required></textarea>
                            	<br>
                            	<button class="btn-block btn-white form-control info" type="submit">Cadastrar</button>
                           </div>
                         </form>
                     </div>
                </div>
            </div>
			<mtag:footerHtml/>
        </div>
    </div>
  </div>
</div>
<!-- /body -->
<mtag:cssJsFoot/>

<mtag:carregaImagem/>

</body>
</html>
