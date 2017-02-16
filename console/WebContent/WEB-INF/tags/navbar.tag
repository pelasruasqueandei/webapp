<%@ attribute name="qtdAlertas" required="true" %>
<%@ attribute name="uid" required="true" %>
<%@ attribute name="index" required="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!-- navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
 <!-- .container-fluid -->
  <div class="container-fluid"> 
    <!-- agrupa Brand e Toggle para melhor visualização em dispositivos menores -->
    <!-- .navbar-header -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navBar">
      <span class="sr-only">Alternar Navegação</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand logo" href="home"><mtag:logoImage/></a>
    </div>
    <!-- /.navbar-header -->
    <!-- .navbar-collapse -->
    <div class="collapse navbar-collapse" id="navBar">
     <ul class="nav navbar-nav">
     	<c:if test="${index eq true}">
     		<li><a href="#corpo">Home</a></li>
     	</c:if>
        <c:if test="${index eq false}">
     		<li><a href="home">Home</a></li>
     	</c:if>
        <c:if test="${index eq true}">
        	<li class="dropdown" data-toggle="tooltip" title="Dashboard">
            	<a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                	Dashboard<span class="caret white"></span></a>
          		<ul class="dropdown-menu" role="menu">
          			<li><a href="cadastrarPonto">Cadastrar Ponto Turístico</a></li>
          			<li class="divider"></li>
            		<li><a href="#ucad">Usuários cadastrados no mês</a></li>
            		<li><a href="#pad">Pontos adicionados por mês</a></li>
            		<li><a href="#pat">Pontos atualizados por mês</a></li>
          		</ul>
        	</li>
         </c:if>
     </ul>
     <ul class="nav navbar-nav navbar-right">
     	<li><a href="listaLogs" data-toggle="tooltip" title="Logs do sistema">
     		<span class="glyphicon glyphicon-list-alt white"></span></a>
     	</li>
        <li class="dropdown" data-toggle="tooltip" title="Gerenciamento de notificações">
            <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                 <span class="glyphicon glyphicon-envelope white"></span>
                 <span class="caret white"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
            	<li><a href="mensagens"><span class="glyphicon glyphicon-envelope"></span> Enviar mensagens aos usuários</a></li>
            </ul>
         </li>
            <mtag:alertasLi qtdAlertas="${qtdAlertas}"/>
            <li class="dropdown" data-toggle="tooltip" title="Configurações de perfil">
             <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                 <mtag:loginIcon src="mostraFoto?id=${uid}"/></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="gerenciarPerfil"><span class="glyphicon glyphicon-user"></span> Gerenciar perfil</a></li>
              <li class="divider"></li>
              <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
            </ul>
            </li>
       </ul>
    </div>
    <!-- /.navbar-collapse --> 
  </div>
  <!-- /.container-fluid --> 
</nav>
<!-- /navbar -->