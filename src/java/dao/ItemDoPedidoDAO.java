/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ItemDoPedido;
import model.Pedido;

/**
 *
 * @author llarruda
 */
public class ItemDoPedidoDAO {
    private String insertItemPedido = "INSERT INTO item_do_pedido (id_pedido, id_produto, qtdade) VALUES (?, ?, ?);";
    private String selectItemPedido = "SELECT * FROM item_do_pedido WHERE id = ? ";
    private String updateItemPedido = "UPDATE item_do_pedido set nome = ?, sobrenome = ?, cpf = ? WHERE id = ?";
    private String deleteItensPedido = "DELETE FROM item_do_pedido WHERE id_pedido = ?;";
    private String selectListaItemPedido = "SELECT id, nome, sobrenome, cpf FROM item_do_pedido;";
    
    /**
    * Método para inserir um itemPedido
    * @param itemPedido - um objeto do tipo itemPedido
    * return void
    * @throws SQLException 
    */
    public void insertItemPedido(ItemDoPedido itemPedido, Pedido pedido) throws SQLException {
       
        Connection con = null;
        PreparedStatement pstmtInsert = null;
        
        try {
            con = new ConnectionFactory().getConnection();
            
            pstmtInsert = con.prepareStatement(insertItemPedido, Statement.RETURN_GENERATED_KEYS);
            // @DEGUB
            System.out.println(pstmtInsert);
            
            pstmtInsert.setInt(1, pedido.getId());
            pstmtInsert.setInt(2, itemPedido.getProduto().getId());
            pstmtInsert.setInt(3, itemPedido.getQuantidade());
            
            // @DEGUB
            System.out.println(pstmtInsert);
            
            pstmtInsert.executeUpdate();
            
            // @DEGUB
            System.out.println("Passou do execute");
            
            // faz o commit da transação DML
            con.commit();
            
            // @DEBUG
            System.out.println("Dados registrados com sucesso.");
            
            ResultSet rs = pstmtInsert.getGeneratedKeys();
            rs.next();
            int i = rs.getInt(1);
            // Não precisamos das primary key dos itens na aplicação (talvez nem delas na modelagem)
            //itemPedido.getId();
            
            // @DEGUB Retorno do Result Set
            System.out.println("Id utilizado: " + i);
            
        } catch (SQLException e) {
            System.out.println("Erro operações itemPedido: insert");
        } finally {
            try { pstmtInsert.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar stmt.");}
            try { con.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar conexão.");}
        }  
    }
    
    /**
    * Método para deletar todos os itens de um pedido
    * @param Pedido - um objeto do tipo itemPedido
    * return Pedido
    * @throws SQLException 
    */
    public Pedido deleteItensPedido(Pedido pedido) throws SQLException {

      Connection con = null;
      PreparedStatement pstmtDelete = null;
      

       try {
            con = new ConnectionFactory().getConnection();

            pstmtDelete = con.prepareStatement(deleteItensPedido);

            // deletando itens do pedido para deletar o pedido;


            // @DEGUB
            System.out.println(pstmtDelete);

            pstmtDelete.setInt(1, pedido.getId());

            // @DEGUB
            System.out.println(pstmtDelete);

            pstmtDelete.executeUpdate();

            // @DEGUB
            System.out.println("Passou do execute");

            // faz o commit da transação DML
            con.commit();

            // @DEBUG
            System.out.println("Dados registrados com sucesso.");


        } catch (SQLException e) {
            System.out.println("Erro operações itemPedido: delete by id");
        } finally {
          
           try { pstmtDelete.close(); } catch (SQLException e) {throw new RuntimeException("Ocorreu erro ao fechar o PreparedStatement.");}
           try { con.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar a conexão com o banco de dados.");}
        }
        return pedido;
    }

}
