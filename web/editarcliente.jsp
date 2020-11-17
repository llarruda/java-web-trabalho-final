<%-- 
    Document   : editarcliente
    Created on : 22/10/2020, 21:06:38
    Author     : Junior
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.js" integrity="sha512-0XDfGxFliYJPFrideYOoxdgNIvrwGTLnmK20xZbCAvPfLGQMzHUsaqZK8ZoH+luXGRxTrS46+Aq400nCnAT0/w==" crossorigin="anonymous"></script>        
        
        <title>Editar Cliente</title>
    </head>
    <body style="background-color: #F8F9FA;">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/home">SGP</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/clientes/list">Clientes<span class="sr-only">(current)</span></a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Produtos</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Pedidos</a>
              </li>
            </ul>
          </div>
        </nav>
        <h2 class="text-center">Editar Cliente</h2>
        <br>
        <br>
        <div class="row-sm">
            <div class="container col-md-4">
                <c:if test="${existecpf == true}">
                    <div class="alert alert-danger" role="alert">
                        Já existe cliente com esse CPF!
                    </div>
                    <c:remove var="existecpf" scope="session"/>
                </c:if>
                <div class="card">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/clientes/atualizar" method="POST">
                            <input type="hidden" name="id" value="${cliente.id}"/>
                            <div class="form-group">
                                <label>Nome</label>
                                <input type="text" class="form-control" name="nome" value="${cliente.nome}" required minlength="3" maxlength="80">                        
                            </div>
                            <div class="form-group">
                                <label>Sobrenome</label>
                                <input type="text" class="form-control" name="sobrenome" value="${cliente.sobreNome}" required minlength="3" maxlength="80">  
                            </div>
                            <div class="form-group">
                                <label>CPF</label>
                                <input type="text" class="form-control" name="cpf" id="cpf" value="${cliente.cpf}" required minlength="11" maxlength="11" pattern="^(\d{3}\.\d{3}\.\d{3}-\d{2})|(\d{11})$" title="Digite apenas números">  
                            </div>
                            <button type="submit" class="btn btn-primary">Atualizar</button>
                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/clientes/list" role="button" style="float: right">Voltar</a>
                        </form>                        
                    </div>
                </div>
            </div>
        </div>
        <footer>
            <div class="navbar navbar-light bg-ligh justify-content-center fixed-bottom">
                <p style="padding-top: 30px;">© Engenharia de Software - 2020</p>
            </div>
        </footer>                             
        <script>
            $(document).ready(function(){
              $('#cpf').mask('999.999.999-99');
            });
        </script>
        
    </body>
</html>