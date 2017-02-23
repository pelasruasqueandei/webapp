<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>Console - Atualiza��o Ponto Tur�stico</title>

<mtag:cssJsHead/>

</head>
<body id="pagina" background="<c:url value="resources/images/main_backimage_large.png" />">
<mtag:navbar qtdAlertas="${qtdAlertas}" uid="${usuario.id}" index="false"/>
<!-- body -->
<div id="corpo" class="container-fluid nav-padded">
  <div class="row">
  	<div class="col-md-6">
    	<div class="row">
            <div id="form" class="panel panel-default nav-padded">
                <mtag:mensagem msg="${mensagem}"/>
            	<div class="panel-heading text-center">
                	<h3>Atualiza��o ponto tur�stico</h3>
                </div>
            	<div class="panel-body">
                	<div class="row">
                        <form class="form-group" method="post" action="atualizaPonto" enctype="multipart/form-data">
                        	<div class="col-md-6">
                        		Foto:
                           	    <div class="fileupload">
                                    <mtag:loginImage src="imagemPonto?id=${ponto.id}"/>
                                    <input type="file" id="foto" name="image">
                                </div>
                            </div>
                       	    <div class="col-md-6">
                       	    	<input type="hidden" name="id" value="${ponto.id}">
                        	    <br>
                        	    Nome:
                            	<input name="nome" type="text" class="form-control" placeholder="Nome" value="${ponto.nome}" required autofocus>
                            	Tipo:
                            	<select name="tipo" class="form-control">
    								<c:if test="${ponto.tipo eq 'Local'}">
    									<option value = "Local">Local</option>
    									<option value = "Monumento">Monumento</option>
    								</c:if>
    								<c:if test="${ponto.tipo eq 'Monumento'}">
    									<option value = "Monumento">Monumento</option>
    									<option value = "Local">Local</option>
    								</c:if>
                                </select>
                                Latitude:
                                <input id="latitude" name="latitude" type="text" class="form-control" placeholder="Latitude" value="${ponto.latitude}" required readonly>
                                Longitude:
                            	<input id="longitude" name="longitude" type="text" class="form-control" placeholder="Longitude" value="${ponto.longitude}" required readonly>
                            	Endere�o:
                            	<input id="endereco" name="endereco" type="text" class="form-control" placeholder="Endere�o" value="${ponto.endereco}" required>
                            	CEP:
								<input id="cep" name="cep" type="text" class="form-control" placeholder="digite o cep para pesquisa ou escolha o seu local no mapa" value="${ponto.cep}" required>
								N�mero:
								<input id="numero" name="numero" type="text" class="form-control" value="${ponto.numero}" required>
								Bairro:
								<input id="bairro" name="bairro" type="text" class="form-control" value="${ponto.bairro}" required>
								Cidade:
								<input id="cidade" name="cidade" type="text" class="form-control" value="${ponto.cidade}" required>
								Estado:
								<input id="estado" name="estado" type="text" class="form-control" value="${ponto.estado}" required>
								<hr>
								Hist�ria:
								<textarea name="historia" rows="5" cols="5" class="form-control" placeholder="Hist�ria" required> ${ponto.historia}</textarea>
                            	<br>
                            	<button class="btn-block btn-white form-control info" type="submit">Atualizar</button>
                           </div>
                         </form>
                     </div>
                </div>
            </div>
			<mtag:footerHtml/>
        </div>
    </div>
    <div id="mapcontainer" class="col-md-6">
  		<div id="map" class="container"></div>
  	</div>
  </div>
</div>
<!-- /body -->
<mtag:cssJsFoot/>

<mtag:carregaImagem/>

<script type="text/javascript" charset="utf-8">
  	var map;
	var marker;
	var myPos = {lat: -8.063206513853977, lng: -34.87147623345186};
	
	function initMap() {
		map = new google.maps.Map(document.getElementById('map'), {
			center: myPos,
			zoom: 18
		});

		marker = new google.maps.Marker({
			position: myPos,
			map: map,
			draggable: false,
			animation: google.maps.Animation.DROP
		});
		
		function toggleBounceOn(marker) {
			marker.setAnimation(null);
			marker.setAnimation(google.maps.Animation.BOUNCE);
		}
	
		function toggleBounceOff(marker) {
			marker.setAnimation(null);
		}
  
		function newPosition(position) {
			map.panTo(position);
			var ponto = ""+position;
			ponto = ponto.replace("(","");
			ponto = ponto.replace(")","");
			
			var latlng = ponto.split(",");
			$("#latitude").val(latlng[0]);
			$("#longitude").val(latlng[1]);
		}
		
		function repos(position){
			marker.setPosition(position);
			//map.setCenter(position);
			newPosition(position);
		}
		
		marker.addListener('mouseover', function(e){
			toggleBounceOn(marker);
		});
	
		marker.addListener('mouseout', function(e){
			toggleBounceOff(marker);
		});
	
		marker.addListener('dragend', function(e){
			var position = e.latLng;
			newPosition(position);
			carregaNosCampos(position);
		});
		
		map.addListener('bounds_changed', function(e){
			var position = map.getCenter();
			marker.setPosition(position);
			newPosition(position);
			carregaNosCampos(position);
		});
	}
	
	$("#endereco").change(function(){
		var endereco = $("#endereco").val();
		var bairro = $("#bairro").val();
		var numero = $("#numero").val();
		carregaNoMapa(map,marker,endereco,bairro,numero);
	});
	
	function carregaNoMapa(map, marker, endereco, bairro, numero){
		$.getJSON("https://maps.googleapis.com/maps/api/geocode/json?address="+endereco+","+numero+","+bairro+"&key=AIzaSyCNgo7zFt-FiFjZtuPzhuDz7VUu87kIFhU&result_type=street_address", function(dados) {
			if (!("erro" in dados)) {
				var lat = dados.results[0].geometry.location.lat;
				var lng = dados.results[0].geometry.location.lng;
				var latlng = new google.maps.LatLng(lat, lng);
				marker.setPosition(latlng);
				map.panTo(latlng);
				map.setCenter(latlng);
			} //end if.
			else {
				alert("Imposs�vel localizar no mapa.");
			}
		});
	}
	
	function carregaNosCampos(position){
		var pos = ""+position;
		pos = pos.replace("(","");
		pos = pos.replace(")","");
		var service = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+pos+"&key=AIzaSyCNgo7zFt-FiFjZtuPzhuDz7VUu87kIFhU&result_type=street_address";
		$.getJSON(service, function(dados) {
			if (!("erro" in dados)) {
				var numero = dados.results[0].address_components[0].long_name;
				var endereco = dados.results[0].address_components[1].long_name;
				var bairro = dados.results[0].address_components[2].long_name;
				var cidade = dados.results[0].address_components[3].long_name;
				var estado = dados.results[0].address_components[5].long_name;
				
				var len = dados.results[0].address_components.length;
				len = len - 1;
				var cep = dados.results[0].address_components[len].long_name;
				cep = cep.replace("-","");
				
				$("#cep").val(cep);
				$("#endereco").val(endereco);
				$("#bairro").val(bairro);
				$("#cidade").val(cidade);
				$("#estado").val(estado);
				$("#numero").val(numero);
			
				//$("#cep").blur();
				repos(position);
			} //end if.
        	else {
        		alert("N�o foi poss�vel localizar o endere�o: "+dados.status);
        	}
		});
	}
  </script>
  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCNgo7zFt-FiFjZtuPzhuDz7VUu87kIFhU&callback=initMap" charset="utf-8"></script>
  <mtag:buscaCep/>
</body>
</html>
