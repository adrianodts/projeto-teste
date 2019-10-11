<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customTags"%>

<customTags:page bodyClass="" title="">
	
    <div class="jumbotron">
		<div class="container">
	        <hr>
			<div class="row">
  				<div class="col-md-offset-1 col-md-10 col-md-offset-1">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">Alteração de Cliente</h3>
						</div>
						<div class="panel-body">
							<p>Preencha os dados do cliente</p>
						</div>
						<c:if test="${not empty msg}">
							<div class="alert alert-danger alert-dismissible" role="alert">
								<button type="button" class="close" data-dismiss="alert" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<strong>${msg}</strong>
							</div>
						</c:if>
						<spring:hasBindErrors name="cliente">
							<ul>
								<c:forEach var="error" items="${errors.allErrors}">
									<li><spring:message message="${error}" /></li>
								</c:forEach>
							</ul>
						</spring:hasBindErrors>
						<div class="container">
							<form:form class="form-horizontal" action="${spring:mvcUrl('CC#save').build()}" method="post" commandName="cliente">
								<fieldset>	
									<form:hidden path="codigoCliente" />
									
									<div class="col-md-12 control-label">
										<p class="help-block"><h11>*</h11>Campo Obrigatório</p>
									</div>

									<spring:bind path="nome">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Nome <h11>*</h11></label>
											<div class="col-sm-10">
												<form:input path="nome" type="text" value="${cliente.nome}"  class="form-control " id="nome" placeholder="Nome" />
											</div>
										</div>
									</spring:bind>

									<spring:bind path="documento">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">CPF <h11>*</h11></label>
											<div class="col-sm-10">
												<form:input path="documento" type="text" value="${cliente.documento}"  class="form-control " id="documento" placeholder="Apenas Números" maxlength="11" pattern="[0-9]+$"/>
											</div>
										</div>
									</spring:bind>

									<spring:bind path="email">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Email <h11>*</h11></label>
											<div class="col-sm-10">
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
													<form:input path="email" type="text" value="${cliente.email}" class="form-control " id="email" placeholder="email@email.com" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" />
												</div>
											</div>
										</div>
									</spring:bind>
									
									<spring:bind path="senha">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Senha <h11>*</h11></label>
											<div class="col-sm-10">
												<form:password path="senha" value="${cliente.senha}" class="form-control" id="senha" placeholder="******" maxlength="10"/>
											</div>
										</div>
									</spring:bind>														
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn btn-primary">Atualizar</button>
											<a href="${spring:mvcUrl('CC#list').build()}" class="btn btn-default">Listar Clientes</a>
										</div>
									</div>
								</fieldset>
							</form:form>
						</div>
					</div>
				</div>
			</div>
      	</div>
    </div>
</customTags:page>