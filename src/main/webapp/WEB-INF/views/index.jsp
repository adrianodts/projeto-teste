<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customTags"%>

<customTags:page bodyClass="" title="">
    <div class="jumbotron">
      <hr>
      <div class="container">
        <h1>Seja bem vindo!</h1>
        <p>Este é um template bootstrap para a criação do projeto teste.</p>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-sm-2 alert alert-success">
          <h2>Home</h2>
          <hr>
          <p>Funcionalidade base para exibir a mensagem de boas vindas. Através da home pode-se ter acesso a todas as funcionalidades requisitadas. Ao clicar no botão home, será disparada uma chamada à action [index] do [HomeController] e o usuário será redirecionado à pagina inicial.</p>
          <hr>
          <p><a class="btn btn-default" href="${spring:mvcUrl('HC#index').build()}">Home</a></p>
        </div>                
        <div class="col-sm-1 verticalLine"></div>
        <div class="col-lg-2 alert alert-info">
          <h2>Clientes</h2>
          <hr>
          <p>Através dessa funcionalidade, pode-se consultar, cadastrar, atualizar e excluir um cliente. Ao clicar no botão Clientes, será disparada uma chamada à action [list] do [ClienteController] e o usuário será redirecionado à pagina de listagem de clientes cadastrados na base de dados.</p>
          <p><a class="btn btn-default" href="${spring:mvcUrl('CC#list').build()}">Clientes</a></p>
       </div>
       <div class="col-sm-1 verticalLine"></div>
       <div class="col-lg-2 alert alert-warning">
          <h2>Assinaturas</h2>
          <hr>
          <p>Através dessa funcionalidade, pode-se efetivar uma assinatua para um determinado cliente. Ao clicar no botão Assinaturas, será disparada uma chamada à action [listAll] do [AssinaturaController] e o usuário será redirecionado à pagina de lista com todas as assinaturass cadastradas</p>
          <hr>
          <p><a class="btn btn-default" href="${spring:mvcUrl('AC#listAll').build()}">Assinaturas</a></p>
       </div>
       <div class="col-sm-1 verticalLine"></div>
       <div class="col-lg-3 alert alert-danger">
          <h2>Area Restrita</h2>
          <hr>
          <p>Através dessa funcionalidade, pode-se ter acesso a área restrita do cliente. Ao clicar no botão Minhas Assinaturas, será disparada uma chamada à action [listarAssinaturas] do [ClienteController] e o usuário será redirecionado à pagina onde será exibida todas as suas assinaturas</p>
          <hr>
          <p><a class="btn btn-default" href="${spring:mvcUrl('CC#listarAssinaturas').build()}">Minhas Assinaturas</a></p>
       </div>           
      </div>
      </div>  
</customTags:page>