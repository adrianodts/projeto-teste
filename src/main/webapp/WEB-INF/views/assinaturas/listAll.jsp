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
							<h3 class="panel-title">Listagem de Assinaturas</h3>
						</div>				
						<c:choose> 
							<c:when test="${empty assinaturas}">						
								<div class="container">
									<div class="row">
										<div class="col-md-3">
										</div>
										<div class="col-md-6">
											<h3>Não há assinaturas cadastradas</h3>
											<p>Para incluir nova assinatura clique no botão abaixo</p>
											<p>
												<a class="btn btn-primary" href="${spring:mvcUrl('AC#form').build()}">Nova assinatura</a>
											</p>
										</div>
										<div class="col-md-3">
										</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="panel-body">
									<a class="btn btn-primary" href="${spring:mvcUrl('AC#form').build()}">Nova assinatura</a>
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
													<th>Codigo Assinatura</th>
													<th>Produto</th>
													<th>CPF</th>
													<th>Nome</th>
													<th>Status</th>
													<th colspan="1"></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${assinaturas}" var="assinatura">
													<tr>
														<td>${assinatura.codigoAssinatura}</td>
														<td>${assinatura.produto.descricao}</td>
														<td>${assinatura.cliente.documento}</td>
														<td>${assinatura.cliente.nome}</td>
														<td>
															<c:choose>
																<c:when test='${not assinatura.ativo}'><c:out value='INATIVA' /></c:when>
																<c:otherwise><c:out value='ATIVA' /></c:otherwise>
															</c:choose>
														</td>
														<td>
															<form:form class="form-horizontal" action="${spring:mvcUrl('AC#cancelarAssintura').arg(0,assinatura.codigoAssinatura).build()}" method="post" commandName="assinatura">
																<button type="submit" class="btn btn-danger btn-xs" onclick="return confirm('Deseja mesmo cancelar a assinatura do produto:  ${assinatura.produto.descricao} ?')">
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