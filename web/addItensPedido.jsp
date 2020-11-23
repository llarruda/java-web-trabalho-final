<%-- 
    Document   : addItensPedidos
    Created on : Nov 22, 2020, 12:08:24 PM
    Author     : llarruda
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        .table-overflow {
            max-height:300px;
            overflow-x:auto;
        }
        
        li {
            text-align: center;
        }
    </style>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.js" integrity="sha512-0XDfGxFliYJPFrideYOoxdgNIvrwGTLnmK20xZbCAvPfLGQMzHUsaqZK8ZoH+luXGRxTrS46+Aq400nCnAT0/w==" crossorigin="anonymous"></script>
        
        <title>Novo Cliente</title>
    </head>
    <body>

        <h2 class="text-center">Adicionar Itens</h2>
        <br>
        <br>
        <div class="row-sm">
            <div class="container col-md-4">
                <c:if test="${descricaoExists == true}">
                    <div class="alert alert-danger" role="alert">
                        Já existe produto cadastrado com a descrição informada!
                    </div>
                    <c:remove var="descricaoExists" scope="session"/>
                </c:if>
                <div class="table-overflow">
                <table class="table table-bordered">
                    <tr>
                        <th scope="col" style="width: 0.1%">ID</th>
                        <th scope="col" style="width: 1%">Descrição</th>
                         <th scope="col" style="width: 1%">Ação</th>
                    </tr>
                    <c:forEach var="produto" items="${listaProdutos}">
                        <tr>
                            <td>${produto.id}</td>
                            <td>${produto.descricao}</td>
                            <td>
                                <div class="form-row">
                                    
                                        <input class="userinput" type="hidden" name="id" value="${produto.id} - ${produto.descricao}">
                                        <button onclick="addItens(this)" class="add btn btn-primary">Adicionar</button>
                                    
<!--                                    <form action="excluir" method="POST">
                                        <input type="hidden" name="id" value="${cliente.id}"">
                                        <button class="btn btn-danger" data-catid="${cliente.id}" data-toggle="modal" data-target="#delete">Excluir</button>
                                    </form>-->
                                    <button onclick="remIten(this)" class="btn btn-danger" data-catid="${produto.id}" data-toggle="modal" data-target="#delete">Remover</button>
                                </div>    
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                </div>
            </div>
        </div>
        <br>
        <h2 class="text-center">Detalhe do Pedido</h2>
        <div class="row-sm">
            <div class="container">
                <br>
                <c:if test="${sucessomsg == true}">
                    <div class="alert alert-success" role="alert">
                        Debug: Pedido registrado!
                        ID <span class="cpf" style="font-weight: bold;">${pedido_id}</span>
                    </div>
                    <c:remove var="sucessomsg" scope="session"/>
                </c:if>
                <c:if test="${excluirmsg == true}">
                    <div class="alert alert-danger" role="alert">
                        Pedido excluido!
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
                   
                    
                    <form id="formPedidos" action="${pageContext.request.contextPath}/pedidos/addItens" method="POST" accept-charset="iso-8859-1, utf-8">
                        <div id="teste" class="form-group">
                            <li class="list-group-item">Itens</li>
                        </div>

                        <button form="formPedidos" type="submit" class="btn btn-success">Finalizar Pedido</button>
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/produtos/list" role="button" style="float: right">Voltar</a>
                    </form>
                    
                </table>
            </div>
            </div>
        </div>
        
        <script>
           $(document).ready(function(){
             $('#cpf').mask('999.999.999-99');
           });


            var clicks = 0;
            var quantidade = 1;
            var list = [];
            
            contadorNomeInputs = 0;
            
            var listInserir = [];
            
           //var buttons = document.getElementsByClassName("add");
           function addItens(elem) {
  
            var $this = $(elem); //< -- wrap the element in a jQuery wrapper
            var val = $this.siblings('input[type=hidden]').val();
            console.log(val);
            
            
            // se não for clicado antes (não houver na lista)
            if (!(list.includes(val))) {
                list.push(val);
                
                ++contadorNomeInputs;
                
                var li = document.createElement("li");
                li.className = 'list-group-item';
                
                var input = document.createElement("input");
                input.setAttribute('type', 'text');
                
                var idProd = val.substr(0, val.indexOf('-'));
                //li.innerHTML = "<label>" + val + "</label>" + "<input type=\"text\" class=\"form-control\" name=\"quantidade" + "[" +  contadorNomeInputs + "]" +"\" placeholder=\"quantidade\"></input>" +
                //"<input type=\"hidden\" class=\"form-control\" name=\"id" + "[" +  contadorNomeInputs + "]" +"\" value=\"" + idProd + "\" readonly></input>";
                
                li.innerHTML = "<label>" + val + "</label>" + "<input form=\"formPedidos\" type=\"text\" class=\"form-control\" name=\"quantidade\" placeholder=\"quantidade\" required minlength=\"1\"></input>" +
                "<input form=\"formPedidos\" type=\"hidden\" class=\"form-control\" name=\"id\" value=\"" + idProd + "\" readonly></input>";
                
                
                var ul = document.getElementById("teste");
                ul.appendChild(li);
                
                if (quantidade > 1) {
                    itenAtual = [val, quantidade];
                    listInserir.push(itenAtual);
                    quantidade = 1;
                    console.log(listInserir);
                }
                console.log(list, quantidade);
            } else {
                ++quantidade;
                console.log(list, quantidade);
            }
            
           }
           
           function remIten(elem) {
               
            list = [];
               
            var $this = $(elem); //< -- wrap the element in a jQuery wrapper
            var val = $this.siblings('input[type=hidden]').val();
            
            var elem = document.getElementById('resumo');
            while (elem.firstChild) {
                elem.removeChild(elem.firstChild);
            }
           }
        </script>
    </body>
</html>
