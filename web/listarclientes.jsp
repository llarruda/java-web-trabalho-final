<%-- 
    Document   : listarclientes
    Created on : 21/10/2020, 21:08:07
    Author     : Junior
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Clientes cadastrados: </h1>
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
                    </tr>
                </c:forEach>
        </table>
    </body>
</html>
