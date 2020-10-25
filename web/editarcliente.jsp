<%-- 
    Document   : editarcliente
    Created on : 22/10/2020, 21:06:38
    Author     : Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Cliente</title>
    </head>
    <body>
        <form action="atualizar" method="POST">
            <table>
                <tr>
                <input type="hidden" name="id" value="${cliente.id}"/>
                    <th>Nome</th>
                    <td><input type="text" name="nome" value="${cliente.nome}"/></td>
                    <th>Sobrenome</th>
                    <td><input type="text" name="sobrenome" value="${cliente.sobreNome}"/></td>
                    <th>CPF</th>
                    <td><input type="number" name="cpf" value="${cliente.cpf}"/></td>
                </tr>
            </table>
                        <button type="submit">Atualizar</button>
        </form>
    </body>
</html>
