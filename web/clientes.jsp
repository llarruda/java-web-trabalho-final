<%-- 
    Document   : newjsp
    Created on : 22/10/2020, 20:16:15
    Author     : Junior
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <title>Clientes</title>
    </head>
    <body>
        
        <h2 class="text-center">Lista de Clientes</h2>
        
        <div class="row-sm">
            <div class="container">

                <form action="novo">
                    <button type="submit" class="btn btn-success">Novo Cliente</button>
                </form>
                <br>
                <table class="table table-bordered">
                    <tr>
                        <th scope="col" style="width: 1%">ID</th>
                        <th scope="col" style="width: 1%">Nome</th>
                        <th scope="col" style="width: 1%">Sobrenome</th>
                        <th scope="col" style="width: 1%">CPF</th>
                        <th scope="col" style="width: 1%">Ação</th>
                    </tr>
                    <c:forEach var="cliente" items="${lista}">
                        <tr>
                            <td>${cliente.id}</td>
                            <td>${cliente.nome}</td>
                            <td>${cliente.sobreNome}</td>
                            <td>${cliente.cpf}</td>
                            <td>
                                <div class="form-row">
                                    <form action="editar?id=" method="GET" style="margin-right: 8px">
                                        <input type="hidden" name="id" value="${cliente.id}">
                                        <button type="submit" class="btn btn-primary">Editar</button>
                                    </form>
                                    <form action="excluir" method="POST">
                                        <input type="hidden" name="id" value="${cliente.id}">
                                        <button type="submit" class="btn btn-danger mr-1">Excluir</button>
                                    </form>                                
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
