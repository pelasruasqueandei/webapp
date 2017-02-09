<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>Console - Login</title>

<mtag:cssJsHead/>

</head>
<body background="<c:url value="resources/images/main_backimage_large.png" />">
<div class="container-fluid">
  <br>
  <br>
  <div class="row">
    <div class="col-md-4 col-md-offset-4">
      <div class="panel">
      	<div class="panel-heading center-block">
            <mtag:logoImage/>
        </div>
        <div class="panel-body">
        	<h3>Houve um erro ao tentar se conectar ao banco de dados.</h3><br>
        	<h5>Desculpe...Tente novamente mais tarde.</h3>
        </div>
      </div>
    </div>
  </div>
  <mtag:footerHtml/>
</div>

<mtag:cssJsFoot/>

</body>
</html>
