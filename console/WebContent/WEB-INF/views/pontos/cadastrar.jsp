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
  	<div id="mapcontainer" class="col-md-6">
  		<div id="map" class="container"></div>
  	</div>
	<div class="col-md-6">
    	<div class="row">
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
                        	    Nome:
                            	<input name="nome" type="text" class="form-control" placeholder="Nome" required autofocus>
                            	Tipo:
                            	<select name="tipo" class="form-control">
    								<option value = "Local">Local</option>
    								<option value = "Monumento">Monumento</option>
                                </select>
                                Latitude:
                                <input id="latitude" name="latitude" type="text" class="form-control" placeholder="Latitude" required readonly>
                                Longitude:
                            	<input id="longitude" name="longitude" type="text" class="form-control" placeholder="Longitude" required readonly>
                            	Endereço:
                            	<input id="endereco" name="endereco" type="text" class="form-control" placeholder="Endereço" required>
                            	CEP:
								<input id="cep" name="cep" type="text" class="form-control" placeholder="digite o cep para pesquisa ou escolha o seu local no mapa">
								Número:
								<input id="numero" name="numero" type="text" class="form-control">
								Bairro:
								<input id="bairro" name="bairro" type="text" class="form-control" readonly>
								Cidade:
								<input id="cidade" name="cidade" type="text" class="form-control" readonly>
								Estado:
								<input id="estado" name="estado" type="text" class="form-control" readonly>
								<hr>
								História:
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


<script type="text/javascript" charset="utf-8">
  
	var map;
	var marker;
	var myPos = {lat: -8.063148088614222, lng: -34.87112754627992};
	
	function initMap() {
		map = new google.maps.Map(document.getElementById('map'), {
		center: myPos,
		zoom: 18
		});

		marker = new google.maps.Marker({
			position: myPos,
			map: map,
			draggable: true,
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
  
		function drop() {
			for (var i = 0; i < markerArray.length; i++) {
				setTimeout(function() {
				addMarkerMethod();
				}, i * 200);
			}
		}
		
		function repos(position){
			marker.setPosition(position);
			map.setCenter(position);
			newPosition(position);
		}
		
		function carregaNosCampos(position){
			var pos = ""+position;
			pos = pos.replace("(","");
			pos = pos.replace(")","");
			var service = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+pos+"&key=AIzaSyCNgo7zFt-FiFjZtuPzhuDz7VUu87kIFhU&result_type=street_address";
			$.getJSON(service, function(dados) {
				
                if (!("erro" in dados)) {
					var numero = dados.results[0].address_components[0].long_name;
					var len = dados.results[0].address_components.length;
					len = len - 1;
					var cep = dados.results[0].address_components[len].long_name;
					cep = cep.replace("-","");
					$("#cep").val(cep);
					$("#numero").val(numero);
					
					$("#cep").blur();
					repos(position);
                } //end if.
                else {
                    alert("Não foi possível localizar o endereço: "+dados.status);
                }
            });
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

	}
	
	$("#endereco").change(function(){
		var endereco = $("#endereco").val();
		var bairro = $("#bairro").val();
		var numero = $("#numero").val();
		carregaNoMapa(map,marker,endereco,bairro,numero);
	});
	
  </script>
  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCNgo7zFt-FiFjZtuPzhuDz7VUu87kIFhU&callback=initMap" charset="utf-8"></script>
</body>
</html>
