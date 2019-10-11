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
							<h3 class="panel-title">Inclusão de Assinaturas</h3>
						</div>
						<div class="panel-body">
							<p>Informe o assinante e o produto</p>
						</div>
						<c:if test="${not empty msg}">
							<div class="alert alert-danger alert-dismissible" role="alert">
								<button type="button" class="close" data-dismiss="alert" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<strong>${msg}</strong>
							</div>
						</c:if>
						<spring:hasBindErrors name="assinatura">
							<ul>
								<c:forEach var="error" items="${errors.allErrors}">
									<li><spring:message message="${error}" /></li>
								</c:forEach>
							</ul>
						</spring:hasBindErrors>
						<div class="container">
							<form:form class="form-horizontal" action="${spring:mvcUrl('AC#save').build()}" method="post" commandName="assinatura">
								<fieldset>	
									<div class="col-md-12 control-label">
										<p class="help-block"><h11>*</h11>Campo Obrigatório</p>
									</div>
									<spring:bind path="cliente.codigoCliente">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Cliente <h11>*</h11></label>
											<div class="col-sm-10">
												<form:select path="cliente.codigoCliente" class="form-control" id="cliente" >
													<form:option value="">--Selecione--</form:option>
													<form:options items="${clientes}" itemLabel="nome" itemValue="codigoCliente" />
												</form:select>
											</div>
										</div>
									</spring:bind>	
									<spring:bind path="produto.codigoProduto">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Produto <h11>*</h11></label>
											<div class="col-sm-10">
												<form:select path="produto.codigoProduto" class="form-control" id="produto" >
													<form:option value="">--Selecione--</form:option>
													<form:options items="${produtos}" itemLabel="descricao" itemValue="codigoProduto" />
												</form:select>
											</div>
										</div>
									</spring:bind>									
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn btn-primary">Incluir Assinatura</button>
											<a href="${spring:mvcUrl('AC#listAll').build()}" class="btn btn-default">Listar Assinaturas</a>
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