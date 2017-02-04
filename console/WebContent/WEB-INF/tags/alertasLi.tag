<%@ attribute name="qtdAlertas" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<li>
 <a href="listaAlertas" data-toggle="tooltip" title="Alertas do sistema">
  <span class="glyphicon glyphicon-alert white"></span>
  <c:if test="${qtdAlertas > 0}">
   <span class="badge">${qtdAlertas}</span>
  </c:if>
 </a>
</li>