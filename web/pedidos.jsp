<%-- 
    Document   : pedidos
    Created on : Nov 19, 2020, 11:10:03 AM
    Author     : llarruda
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
        
        <title>Pedidos</title>
    </head>
    <body style="background-color: #F8F9FA;">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/home">SGP</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/clientes/list">Clientes</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/produtos/list">Produtos</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/pedidos/list">Pedidos</a>
              </li>
            </ul>
          </div>
        </nav>
        <h2 class="text-center">Lista de Pedidos</h2>

        <div class="row-sm">
            <div class="container">
                <form action="${pageContext.request.contextPath}/pedidos/novo" method="GET">
                    <button type="submit" class="btn btn-success">Novo Pedido</button>
                </form>
                <br>
                <c:if test="${sucessomsg == true}">
                    <div class="alert alert-success" role="alert">
                        Produto cadastrado!
                    </div>
                    <c:remove var="sucessomsg" scope="session"/>
                </c:if>
                <c:if test="${excluirmsg == true}">
                    <div class="alert alert-danger" role="alert">
                        Produto excluido!
                    </div>
                    <c:remove var="excluirmsg" scope="session"/>
                </c:if>
                <c:if test="${alterarmsg == true}">
                    <div class="alert alert-primary" role="alert">
                        Pedido alterado com sucesso!
                    </div>
                    <c:remove var="alterarmsg" scope="session"/>
                </c:if>                   
                <table class="table table-bordered">
                    <tr>
                        <th scope="col" style="width: 0.2%">ID</th>
                        <th scope="col" style="width: 0.5%">Data</th>
                        <th scope="col" style="width: 0.2%">CPF</th>
                        <th scope="col" style="width: 2.5%">Cliente</th>
                         <th scope="col" style="width: 0.5%">Ação</th>
                    </tr>
                    <c:forEach var="pedido" items="${lista}">
                        <tr>
                            <td>${pedido.id}</td>
                            <td>${pedido.data}</td>
                            <td>${pedido.getCliente().getCpf()}</td>
                            <td>${pedido.getCliente().getNome()} ${pedido.getCliente().getSobreNome()}</td>
                            <td>
                                <div class="form-row">
                                    <form action="${pageContext.request.contextPath}/pedidos/edit?id=" method="GET" style="margin-right: 8px">
                                        <input type="hidden" name="id" value="${produto.id}">
                                        <button type="submit" class="btn btn-primary">Editar</button>
                                    </form>
<!--                                    <form action="excluir" method="POST">
                                        <input type="hidden" name="id" value="${cliente.id}"">
                                        <button class="btn btn-danger" data-catid="${cliente.id}" data-toggle="modal" data-target="#delete">Excluir</button>
                                    </form>-->
                                    <button class="btn btn-danger" data-catid="${produto.id}" data-toggle="modal" data-target="#delete">Excluir</button>
                                </div>    
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            </div>
        </div>
    <footer>
        <div class="navbar navbar-light bg-ligh justify-content-center fixed-bottom">
            <p style="padding-top: 30px;">© Engenharia de Software - 2020</p>
        </div>
    </footer>  
    </body>
</html>
