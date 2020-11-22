<%-- 
    Document   : novoPedido
    Created on : Nov 22, 2020, 11:12:11 AM
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
        
        <title>Novo Pedido</title>
    </head>
    <body>

        <h2 class="text-center">Cadastrar Pedido</h2>
        <br>
        <h4 class="text-center">Informe o Cliente</h4>
        <br>
        <div class="row-sm">
            <div class="container col-md-4">
                <c:if test="${cpfNotFound == true}">
                    <div class="alert alert-danger" role="alert">
                        Cliente não encontrado.
                    </div>
                    <c:remove var="cpfNotFound" scope="session"/>
                </c:if>
                <div class="card">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/pedidos/create" method="POST" accept-charset="iso-8859-1, utf-8">
                            <div class="form-group">
                                <label for="cpf">CPF</label>
                                <input type="text" class="form-control" name="cpf" id="cpf" required minlength="11" maxlength="11" pattern="^(\d{3}\.\d{3}\.\d{3}-\d{2})|(\d{11})$" title="Digite apenas números">  
                            </div>
                        
                            <br>
                            <button type="submit" class="btn btn-success">Seleção de Itens</button>
                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/pedidos/list" role="button" style="float: right">Voltar</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <script>
            $(document).ready(function(){
              $('#cpf').mask('999.999.999-99');
            });
            
            
            var button = document.getElementById("add");
            var input = document.getElementById("userinput");
            var ul = document.querySelector("ul");

            button.addEventListener("click", function() {
              var li = document.createElement("li");
              li.className = 'list-group-item';
              li.textContent = input.value;
              ul.appendChild(li);
            });
        </script>

    </body>
</html>
