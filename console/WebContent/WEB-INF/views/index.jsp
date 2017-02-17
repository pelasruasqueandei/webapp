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

<script type="text/javascript" charset="utf-8">
	var markersurl = "localhost:8081/console/getPontos?type=json";
	//var markersurl = "http://www.pelasruasqueandeiapp.com.br/console/getPontos?type=json"; TODO continuar
	
  	var map;
	var marker;
	var myPos = {lat: -8.063206513853977, lng: -34.87147623345186};
	
	function initMap() {
		map = new google.maps.Map(document.getElementById('map2'), {
			center: myPos,
			zoom: 13
		});

		marker = new google.maps.Marker({
			position: myPos,
			map: map,
			draggable: false
		});
		
		
	}
  </script>
  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCNgo7zFt-FiFjZtuPzhuDz7VUu87kIFhU&callback=initMap" charset="utf-8"></script>

</body>
</html>
