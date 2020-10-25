<%-- 
    Document   : novocliente
    Created on : 25/10/2020, 18:22:14
    Author     : Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo Cliente</title>
    </head>
    <body>
        <form action="criar" method="POST">
            <table>
                <tr>
                    <th>Nome</th>
                    <td><input type="text" name="nome" /></td>
                    <th>Sobrenome</th>
                    <td><input type="text" name="sobrenome" /></td>
                    <th>CPF</th>
                    <td><input type="number" name="cpf" "/></td>
                </tr>
            </table>
            <button type="submit">Criar</button>
        </form>
    </body>
</html>
