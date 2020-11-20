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
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.js" integrity="sha512-0XDfGxFliYJPFrideYOoxdgNIvrwGTLnmK20xZbCAvPfLGQMzHUsaqZK8ZoH+luXGRxTrS46+Aq400nCnAT0/w==" crossorigin="anonymous"></script>
        
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
                <c:if test="${sucessomsg == true}">
                    <div class="alert alert-success" role="alert">
                        Cliente cadastrado!
                    </div>
                    <c:remove var="sucessomsg" scope="session"/>
                </c:if>
                <c:if test="${excluirmsg == true}">
                    <div class="alert alert-danger" role="alert">
                        Cliente excluido!
                    </div>
                    <c:remove var="excluirmsg" scope="session"/>
                </c:if>
                <c:if test="${alterarmsg == true}">
                    <div class="alert alert-primary" role="alert">
                        Cliente alterado com sucesso!
                    </div>
                    <c:remove var="alterarmsg" scope="session"/>
                </c:if>                   
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
                            <td class="cpf">${cliente.cpf}</td>
                            <td>
                                <div class="form-row">
                                    <form action="editar?id=" method="GET" style="margin-right: 8px">
                                        <input type="hidden" name="id" value="${cliente.id}">
                                        <button type="submit" class="btn btn-primary">Editar</button>
                                    </form>
<!--                                    <form action="excluir" method="POST">
                                        <input type="hidden" name="id" value="${cliente.id}"">
                                        <button class="btn btn-danger" data-catid="${cliente.id}" data-toggle="modal" data-target="#delete">Excluir</button>
                                    </form>-->
                                    <button class="btn btn-danger" data-catid="${cliente.id}" data-toggle="modal" data-target="#delete">Excluir</button>
                                </div>    
                            </td>
                        </tr>
                    </c:forEach>
                </table>
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
                        <form action="excluir" method="POST">
                            <div class="modal-body">
                                Deseja excluir esse cliente?
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
        
        <script>
            $('#delete').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget) 
                var cat_id = button.data('catid') 
                var modal = $(this)
                modal.find('.modal-body #cat_id').val(cat_id);
            });
        </script>
        <script>
            $(document).ready(function(){
                $('.cpf').mask('999.999.999-99');
            });
        </script>  
          
    </body>
</html>