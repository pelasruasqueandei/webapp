<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>Console - Logs</title>

<mtag:cssJsHead/>

</head>
<body background="<c:url value="resources/images/main_backimage_large.png" />">
<mtag:navbar qtdAlertas="${alertas}" uid="${usuario.id}" index="false"/>
<!-- body -->
<div id="corpo" class="container-fluid nav-padded">
  <div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <mtag:mensagem msg="${mensagem}"/>
    	${listaLogs}
    </div>
    <div class="col-md-1"></div>
  </div>
  <mtag:footerHtml/>
</div>
<!-- /body -->

<mtag:cssJsFoot/>

</body>
</html>
