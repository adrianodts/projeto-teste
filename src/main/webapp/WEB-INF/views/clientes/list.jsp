<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
							<h3 class="panel-title">Clientes Cadastrados</h3>
						</div>				
						<c:choose> 
							<c:when test="${empty clientes}">						
								<div class="container">
									<div class="row">
										<div class="col-md-3">
										</div>
										<div class="col-md-6">
											<h3>Não há clientes cadastrados</h3>
											<p>Para cadastrar novo cliente clique no botão abaixo</p>
											<p>
												<a class="btn btn-primary" href="${spring:mvcUrl('CC#form').build()}">Novo cliente</a>
											</p>
										</div>
										<div class="col-md-3">
										</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="panel-body">
									<a class="btn btn-primary" href="${spring:mvcUrl('CC#form').build()}">Novo cliente</a>
								</div>
								<div class="container">
									<c:if test="${not empty success}">
										<div class="alert alert-success alert-dismissible" role="alert">
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<strong>${success}</strong>
										</div>
									</c:if>																		
									<div class="table-responsive">
										<table id="datatable"
											class="table table-hover table-striped table-bordered" cellspacing="0" width="100%">
											<thead>
												<tr>
													<th>Nome</th>
													<th>CPF</th>
													<th>Email</th>
													<th colspan="2"></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${clientes}" var="cliente">
													<tr>
														<td>${cliente.nome}</td>
														<td>${cliente.documento}
														<td>${cliente.email}
														<td>											
															<a href="${spring:mvcUrl('CC#show').arg(0,cliente.codigoCliente).build()}" class="btn btn-primary btn-xs"> 
																<span class="glyphicon glyphicon-pencil"></span>
															</a>
														</td>
														<td>
															<form:form class="form-horizontal" action="${spring:mvcUrl('CC#delete').arg(0,cliente.codigoCliente).build()}" method="post" commandName="cliente">
																<button type="submit" class="btn btn-danger btn-xs" onclick="return confirm('Deseja mesmo excluir: ${cliente.nome} ?')">
																	<span class="glyphicon glyphicon-trash"></span>
																</button>
															</form:form>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>					
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</customTags:page>
