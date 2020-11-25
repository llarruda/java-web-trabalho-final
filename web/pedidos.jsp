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
              <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/pedidos/list">Pedidos</a>
              </li>
            </ul>
          </div>
        </nav>
        <h2 class="text-center">Lista de Pedidos</h2>

        <div class="row-sm">
            <div class="container">
                <div class="navbar navbar-expand-lg">
                    <ul class="navbar-nav mr-auto">
                    <form action="${pageContext.request.contextPath}/pedidos/new" method="GET">
                        <button type="submit" class="btn btn-success">Novo Pedido</button>
                    </form>
                    </ul>
                    <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/pedidos/search" method="POST" accept-charset="iso-8859-1, utf-8">
                        <input name="cpf" class="cpf form-control mr-sm-2" type="search" placeholder="CPF Cliente" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar Pedidos</button>
                    </form>
                </div>
                <br>
                <c:if test="${sucessomsg == true}">
                    <div class="alert alert-success" role="alert">
                        Pedido cadastrado!
                    </div>
                    <c:remove var="sucessomsg" scope="session"/>
                </c:if>
                <c:if test="${excluirmsg == true}">
                    <div class="alert alert-danger" role="alert">
                        Pedido excluido!
                    </div>
                    <c:remove var="excluirmsg" scope="session"/>
                </c:if>
                <c:if test="${cpfNotFound == true}">
                    <div class="alert alert-primary" role="alert">
                        Cliente não encontrado.
                    </div>
                    <c:remove var="cpfNotFound" scope="session"/>
                </c:if>
                <c:if test="${clienteSemPedido == true}">
                    <div class="alert alert-danger" role="alert">
                        Cliente ainda não possui nenhum pedido cadastrado. CPF consultado: <span class="cpf" style="font-weight: bold;">${cpf_consultado}</span>.
                    </div>
                    <c:remove var="clienteSemPedido" scope="session"/>
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
                            <td class="rawDate">${pedido.data}</td>
                            <td class="cpf">${pedido.getCliente().getCpf()}</td>
                            <td>${pedido.getCliente().getNome()} ${pedido.getCliente().getSobreNome()}</td>
                            <td>
                                <div class="form-row">
                                    <form action="${pageContext.request.contextPath}/pedidos/detail?id=" method="GET" style="margin-right: 8px">
                                        <input type="hidden" name="id" value="${pedido.id}">
                                        <button type="submit" class="btn btn-primary">Detalhes</button>
                                    </form>
<!--                                    <form action="excluir" method="POST">
                                        <input type="hidden" name="id" value="${cliente.id}"">
                                        <button class="btn btn-danger" data-catid="${cliente.id}" data-toggle="modal" data-target="#delete">Excluir</button>
                                    </form>-->
                                    <button class="btn btn-danger" data-catid="${pedido.id}" data-toggle="modal" data-target="#delete">Excluir</button>
                                </div>    
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            </div>
                        
                         <!-- Modal -->
            <div class="modal modal-danger fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog  modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="TituloModalCentralizado">Confirmar exclusão</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form action="delete" method="POST">
                            <div class="modal-body">
                                Deseja excluir esse pedido?
                                <input type="hidden" name="id" id="cat_id" value="">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-info" data-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-danger">Excluir</button>
                            </div>
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
            $('.cpf').mask('999.999.999-99');
        });
        
        /*$(document).ready(function() {
            var intermediate = $('.rawDate').mask('9999-99-99');
            var mes;
            for (var i =0; i < intermediate.length; i++) {
                var ano = intermediate[i].innerHTML.substring(0,4);
                mes = intermediate[i].innerHTML.substring(5,7);
                var dia = intermediate[i].innerHTML.substring(9,11);
                intermediate.html(mes);
                console.log(intermediate[i].innerHTML);
                console.log(mes);
            }
            $('.rawDate').html('teste');
        });*/
    
        $('.rawDate').each( function() {

            //var format = "Do MMM YYYY";

            var $this = $( this );
            var old_date = $.trim($this.text());
            var ano = old_date.substring(0,4);
            var mes = old_date.substring(5,7);
            var dia = old_date.substring(8,10);
            console.log(old_date);
            var new_date = dia + "/" + mes + "/" + ano;
            $this.text($this.text().replace(old_date, new_date));
            
        });
    </script>
    <script>
        $('#delete').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); 
            var cat_id = button.data('catid'); 
            var modal = $(this);
            modal.find('.modal-body #cat_id').val(cat_id);
        });
    </script>
    </body>
</html>
