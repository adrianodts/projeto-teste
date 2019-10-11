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
							<h3 class="panel-title">ERRO</h3>
						</div>
						<div class="panel-body">
							<p>Ocorreu o seguinte erro:</p>
						</div>
						<div class="container">							
							<p>${exception.message}</p>
						</div>
					</div>
				</div>
			</div>
      	</div>
    </div>
    
</customTags:page>