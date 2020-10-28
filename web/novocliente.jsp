<%-- 
    Document   : novocliente
    Created on : 25/10/2020, 18:22:14
    Author     : Junior
--%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <title>Novo Cliente</title>
    </head>
    <body>

        <h2 class="text-center">Cadastrar Cliente</h2>
        <br>
        <br>
        <div class="row-sm">
            <div class="container col-md-4">
                <div class="card">
                    <div class="card-body">
                        <form action="criar" method="POST">
                            <div class="form-group">
                                <label>Nome</label>
                                <input type="text" class="form-control" name="nome">                        
                            </div>
                            <div class="form-group">
                                <label>Sobrenome</label>
                                <input type="text" class="form-control" name="sobrenome">  
                            </div>
                            <div class="form-group">
                                <label>CPF</label>
                                <input type="number" class="form-control" name="cpf">  
                            </div>
                            <button type="submit" class="btn btn-success">Salvar</button>
                        </form>                        
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
