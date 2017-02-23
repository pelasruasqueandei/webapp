<%@ attribute name="src" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<img id="image${id}" class="center-block" src="<c:url value="/resources/images/logo/Icone-454x454.png" />" alt="Foto do perfil">

<script>
//JavaScript Document
	$('image${id}').height(100);
	$('image${id}').width(100);
	
	var source = "${src}";
	if(source != null && source != ""){
		var output = $('image${id}');
		output.attr('src',source);
	}
</script>