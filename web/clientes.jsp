<%-- 
    Document   : newjsp
    Created on : 22/10/2020, 20:16:15
    Author     : Junior
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
    </head>
    <body>
        <h1>Clientes cadastrados: </h1>
        <h2><a href="novo">Novo Cliente</a></h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Sobrenome</th>
                <th>CPF</th>
            </tr>
                <c:forEach var="cliente" items="${lista}">
                    <tr>
                        <td>${cliente.id}</td>
                        <td>${cliente.nome}</td>
                        <td>${cliente.sobreNome}</td>
                        <td>${cliente.cpf}</td>
                        <td><a href="editar?id=${cliente.id}">Editar</a></td>
                        <form action="excluir" method="POST">
                            <input type="hidden" name="id" value="${cliente.id}">
                            <td><input type="submit" value="Excluir"></td>
                        </form>
                    </tr>
                </c:forEach>
        </table>
    </body>
</html>
