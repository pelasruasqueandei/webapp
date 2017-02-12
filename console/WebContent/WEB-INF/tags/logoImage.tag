<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<img id="logoimage" class="center-block" src="<c:url value="/resources/images/logo/Logo-300x100.png" />" alt="Console Logo">
<script type="text/javascript">
	$(document).ready(function(){
		function resize(){
			var height = 58;
			var width = 300;
			
			var largura = $(window).width();
			if(largura < 840){
				$('#logoimage').attr('src','resources/images/logo/Icone-454x454.png');
				height = 68;
				width = 68;
			}
			else{
				$('#logoimage').attr('src','resources/images/logo/Logo-300x100.png');
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