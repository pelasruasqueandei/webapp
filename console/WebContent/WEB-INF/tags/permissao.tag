<%@ attribute name="value" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${value eq 'Administrador'}">
<select name="permissao" class="form-control">
    <option value = "Administrador">Usu�rio Administrador</option>
    <option value = "Comum">Usu�rio Comum</option>
    <option value = "Desativado">Usu�rio Desativado</option>
</select>
</c:if>
<c:if test="${value eq 'Comum'}">
<select name="permissao" class="form-control">
    <option value = "Comum">Usu�rio Comum</option>
    <option value = "Administrador">Usu�rio Administrador</option>
    <option value = "Desativado">Usu�rio Desativado</option>
</select>
</c:if>
<c:if test="${value eq 'Desativado'}">
<select name="permissao" class="form-control">
    <option value = "Desativado">Usu�rio Desativado</option>
    <option value = "Administrador">Usu�rio Administrador</option>
    <option value = "Comum">Usu�rio Comum</option>
</select>
</c:if>