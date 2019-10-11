<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>	
<html>
	<head>
		<title>Editora Abril S.A.</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="#">Editora Abril S.A. (Teste)</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
			<div id="navbar-menu" class="collapse navbar-collapse">
	          <ul class="nav navbar-nav">
	            <li><a href="${spring:mvcUrl('HC#index').build()}">Home</a></li>
	            <li><a href="${spring:mvcUrl('CC#list').build()}">Clientes</a></li>
	            <li><a href="${spring:mvcUrl('AC#listAll').build()}">Assinaturas</a></li>
	            <c:if test="${sessionScope.usuarioLogado == null}">
	            	<li><a href="${spring:mvcUrl('CC#listarAssinaturas').build()}">Minhas Assinaturas</a></li>
	            </c:if>			
				<c:if test="${sessionScope.usuarioLogado != null}">
					<li>
						<p class="list-group-item list-group-item-warning">Olá ${sessionScope.usuarioLogado.nome}.
							<a href="${spring:mvcUrl('CC#listarAssinaturas').build()}">Minhas Assinaturas</a>
						</p>
						<p class="list-group-item list-group-item-warning">	
							<a href="${spring:mvcUrl('LC#processLogout').build()}">Sair</a>
						</p>
					</li>
				</c:if>	                
	          </ul>
	        </div>
        </div>
       </div>
    </nav>