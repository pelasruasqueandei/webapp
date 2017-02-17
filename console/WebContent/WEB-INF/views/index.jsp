<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>Console Gerenciador de Dados</title>

<mtag:cssJsHead/>

</head>
<body id="pagina" background="<c:url value="resources/images/main_backimage_large.png" />">
<mtag:navbar qtdAlertas="${qtdAlertas}" uid="${usuario.id}" index="true"/>
<div id="corpo" class="container-fluid nav-padded">
  <div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
    	<div class="row text-center">
    		<hr>
            <mtag:mensagem msg="${mensagem}"/>
            <mtag:alertasNaoLidos qtdAlertas="${qtdAlertas}"/>
            <h3>Bem vindo às ruas de nossa cidade maravilhosa!</h3>
        	<hr>
        </div>
    </div>
    <div class="col-md-2"></div>
  </div>
  <div class="row">
  	<div class="col-md-2"></div>
  	<div id="mapcontainer2" class="col-md-8">
  		<div id="map2" class="container"></div>
  	</div>
  	<div class="col-md-2"></div>
  </div>
  <mtag:footerHtml/>
</div>

<mtag:cssJsFoot/>
	${script}
</body>
</html>
