<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>Console - Atualização Ponto Turístico</title>

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
                	<h3>Atualização ponto turístico</h3>
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
                                <hr>
                                Horário de Funcionamento:
                                <hr>
                                <ul class="nav nav-tabs">
                                	<li><a href="#panel-segunda" class="active" data-toggle="tab">Seg</a></li>
                                	<li><a href="#panel-terca" data-toggle="tab">Ter</a></li>
                                	<li><a href="#panel-quarta" data-toggle="tab">Qua</a></li>
                                	<li><a href="#panel-quinta" data-toggle="tab">Qui</a></li>
                                	<li><a href="#panel-sexta" data-toggle="tab">Sex</a></li>
                                	<li><a href="#panel-sabado" data-toggle="tab">Sab</a></li>
                                	<li><a href="#panel-domingo" data-toggle="tab">Dom</a></li>
                                </ul>
                                <div class="tab-content">
                                	<div id="panel-segunda" class="tab-pane fade in active">
                                		<div>
                                			<c:if test="${ponto.funcSegunda eq true}">
                                				<input name="funcSegunda" type="checkbox" checked/>Funciona na Segunda
                                			</c:if>
                                			<c:if test="${ponto.funcSegunda eq false}">
                                				<input name="funcSegunda" type="checkbox"/>Funciona na Segunda
                                			</c:if>
                                			<br>
                                			Início:
                                			<br>
                                			<input name="horarioSegundaInicio" class="form-control" type="time" value="${ponto.horarioSegundaInicio}"/>
                                			<br>
                                			Intervalo:
                                			<br>
                                			<input name="horarioSegundaIntervaloIn" class="form-control" type="time" value="${ponto.horarioSegundaIntervaloIn}"/>
                                			<br>às<br>
                                			<input name="horarioSegundaIntervaloOut" class="form-control" type="time" value="${ponto.horarioSegundaIntervaloOut}"/>
                                			<br>
                                			Fim:
                                			<br>
                                			<input name="horarioSegundaFim" class="form-control" type="time"  value="${ponto.horarioSegundaFim}"/>
                                		</div>
                                	</div>
                                	<div id="panel-terca" class="tab-pane fade">
                                		<div>
                                			<c:if test="${ponto.funcTerca eq true}">
                                				<input name="funcTerca" type="checkbox" checked/>Funciona na Terça
                                			</c:if>
                                			<c:if test="${ponto.funcTerca eq false}">
                                				<input name="funcTerca" type="checkbox"/>Funciona na Terça
                                			</c:if>
                                			<br>
                                			Início:
                                			<br>
                                			<input name="horarioTercaInicio" class="form-control" type="time"/>
                                			<br>
                                			Intervalo:
                                			<br>
                                			<input name="horarioTercaIntervaloIn" class="form-control" type="time"/>
                                			<br>às<br>
                                			<input name="horarioTercaIntervaloOut" class="form-control" type="time"/>
                                			<br>
                                			Fim:
                                			<br>
                                			<input name="horarioTercaFim" class="form-control" type="time"/>
                                		</div>
                                	</div>
                                	<div id="panel-quarta" class="tab-pane fade">
                                		<div>
                                			<c:if test="${ponto.funcQuarta eq true}">
                                				<input name="funcQuarta" type="checkbox" checked/>Funciona na Quarta
                                			</c:if>
                                			<c:if test="${ponto.funcQuarta eq false}">
                                				<input name="funcQuarta" type="checkbox"/>Funciona na Quarta
                                			</c:if>
                                			<br>
                                			Início:
                                			<br>
                                			<input name="horarioQuartaInicio" class="form-control" type="time"/>
                                			<br>
                                			Intervalo:
                                			<br>
                                			<input name="horarioQuartaIntervaloIn" class="form-control" type="time"/>
                                			<br>às<br>
                                			<input name="horarioQuartaIntervaloOut" class="form-control" type="time"/>
                                			<br>
                                			Fim:
                                			<br>
                                			<input name="horarioQuartaFim" class="form-control" type="time"/>
                                		</div>
                                	</div>
                                	<div id="panel-quinta" class="tab-pane fade">
                                		<div>
                                			<c:if test="${ponto.funcQuinta eq true}">
                                				<input name="funcQuinta" type="checkbox" checked/>Funciona na Quinta
                                			</c:if>
                                			<c:if test="${ponto.funcQuinta eq false}">
                                				<input name="funcQuinta" type="checkbox"/>Funciona na Quinta
                                			</c:if>
                                			<br>
                                			Início:
                                			<br>
                                			<input name="horarioQuintaInicio" class="form-control" type="time"/>
                                			<br>
                                			Intervalo:
                                			<br>
                                			<input name="horarioQuintaIntervaloIn" class="form-control" type="time"/>
                                			<br>às<br>
                                			<input name="horarioQuintaIntervaloOut" class="form-control" type="time"/>
                                			<br>
                                			Fim:
                                			<br>
                                			<input name="horarioQuintaFim" class="form-control" type="time"/>
                                		</div>
                                	</div>
                                	<div id="panel-sexta" class="tab-pane fade">
                                		<div>
                                			<c:if test="${ponto.funcSexta eq true}">
                                				<input name="funcSexta" type="checkbox" checked/>Funciona na Sexta
                                			</c:if>
                                			<c:if test="${ponto.funcSexta eq false}">
                                				<input name="funcSexta" type="checkbox"/>Funciona na Sexta
                                			</c:if>
                                			<br>
                                			Início:
                                			<br>
                                			<input name="horarioSextaInicio" class="form-control" type="time"/>
                                			<br>
                                			Intervalo:
                                			<br>
                                			<input name="horarioSextaIntervaloIn" class="form-control" type="time"/>
                                			<br>às<br>
                                			<input name="horarioSextaIntervaloOut" class="form-control" type="time"/>
                                			<br>
                                			Fim:
                                			<br>
                                			<input name="horarioSextaFim" class="form-control" type="time"/>
                                		</div>
                                	</div>
                                	<div id="panel-sabado" class="tab-pane fade">
                                		<div>
                                			<c:if test="${ponto.funcSabado eq true}">
                                				<input name="funcSabado" type="checkbox" checked/>Funciona na Sabado
                                			</c:if>
                                			<c:if test="${ponto.funcSabado eq false}">
                                				<input name="funcSabado" type="checkbox"/>Funciona na Sabado
                                			</c:if>
                                			<br>
                                			Início:
                                			<br>
                                			<input name="horarioSabadoInicio" class="form-control" type="time"/>
                                			<br>
                                			Intervalo:
                                			<br>
                                			<input name="horarioSabadoIntervaloIn" class="form-control" type="time"/>
                                			<br>às<br>
                                			<input name="horarioSabadoIntervaloOut" class="form-control" type="time"/>
                                			<br>
                                			Fim:
                                			<br>
                                			<input name="horarioSabadoFim" class="form-control" type="time"/>
                                		</div>
                                	</div>
                                	<div id="panel-domingo" class="tab-pane fade">
                                		<div>
                                			<c:if test="${ponto.funcDomingo eq true}">
                                				<input name="funcDomingo" type="checkbox" checked/>Funciona na Domingo
                                			</c:if>
                                			<c:if test="${ponto.funcDomingo eq false}">
                                				<input name="funcDomingo" type="checkbox"/>Funciona na Domingo
                                			</c:if>
                                			<br>
                                			Início:
                                			<br>
                                			<input name="horarioDomingoInicio" class="form-control" type="time"/>
                                			<br>
                                			Intervalo:
                                			<br>
                                			<input name="horarioDomingoIntervaloIn" class="form-control" type="time"/>
                                			<br>às<br>
                                			<input name="horarioDomingoIntervaloOut" class="form-control" type="time"/>
                                			<br>
                                			Fim:
                                			<br>
                                			<input name="horarioDomingoFim" class="form-control" type="time"/>
                                		</div>
                                	</div>
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
                            	Endereço:
                            	<input id="endereco" name="endereco" type="text" class="form-control" placeholder="Endereço" value="${ponto.endereco}" required>
                            	CEP:
								<input id="cep" name="cep" type="text" class="form-control" placeholder="digite o cep para pesquisa ou escolha o seu local no mapa" value="${ponto.cep}" required>
								Número:
								<input id="numero" name="numero" type="text" class="form-control" value="${ponto.numero}" required>
								Bairro:
								<input id="bairro" name="bairro" type="text" class="form-control" value="${ponto.bairro}" required>
								Cidade:
								<input id="cidade" name="cidade" type="text" class="form-control" value="${ponto.cidade}" required>
								Estado:
								<input id="estado" name="estado" type="text" class="form-control" value="${ponto.estado}" required>
								<hr>
								História:
								<textarea name="historia" rows="5" cols="5" class="form-control" placeholder="História" required> ${ponto.historia}</textarea>
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
				alert("Impossível localizar no mapa.");
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
			} //end if.
        	else {
        		alert("Não foi possível localizar o endereço: "+dados.status);
        	}
		});
	}
  </script>
  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCNgo7zFt-FiFjZtuPzhuDz7VUu87kIFhU&callback=initMap" charset="utf-8"></script>
  <mtag:buscaCep/>
</body>
</html>
