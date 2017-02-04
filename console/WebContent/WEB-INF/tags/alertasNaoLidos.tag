<%@ attribute name="qtdAlertas" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${qtdAlertas > 0}">
 <div id="alert-closeable" class="alert alert-info">
   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   <a href="listaAlertas">H� alertas n�o lidos no sistema.</a>
 </div>
</c:if>