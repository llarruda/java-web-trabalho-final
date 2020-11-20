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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.ItemDoPedido;
import model.Pedido;
import model.Produto;

/**
 *
 * @author llarruda
 */
public class PedidoDAO {
    private final String insertPedido = "INSERT INTO pedido (data, id_cliente) VALUES (?, ?);";
    private final String selecPedido = "SELECT data, id_cliente FROM pedido WHERE id_cliente = ? ";
    private final String updatePedido = "UPDATE pedido set data = ?, id_cliente = ? WHERE id_cliente = ?";
    private final String deletePedido = "DELETE FROM pedido WHERE id = ?;";
    private final String selectListaPedido = "SELECT id, data, id_cliente FROM pedido WHERE id_cliente = ?;";
    private final String selectListaItensPedido = "SELECT i.id_item, i.id_pedido, i.id_produto, prod.descricao, i.qtdade FROM item_do_pedido AS i\n" +
                                                "INNER JOIN produto AS prod ON i.id_produto = prod.id\n" +
                                                "INNER JOIN pedido as p ON p.id = i.id_pedido\n" +
                                                "WHERE p.id = ?;";
    
    /**
    * Método para inserir um Pedido
    * @param pedido - um objecto do tipo Pedido
    * @return pedido - O objeto do tipo Pedido com o id usado para gravar no banco
    * @throws SQLException 
    */
    public Pedido insertPedido(Pedido pedido) throws SQLException {
       
        Connection con = null;
        PreparedStatement pstmtInsert = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        try {
            con = new ConnectionFactory().getConnection();
            
            pstmtInsert = con.prepareStatement(insertPedido, Statement.RETURN_GENERATED_KEYS);
            // @DEGUB
            System.out.println(pstmtInsert);
            
            pstmtInsert.setString(1, pedido.getData().format(formatter));
            pstmtInsert.setInt(2, pedido.getCliente().getId());
            
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
            pedido.setId(i);
            
            // @DEGUB
            System.out.println("Id utilizado: " + pedido.getId());
            
        } catch (SQLException e) {
            System.out.println("Erro operações Pedido: insert");
        } finally {
            try { pstmtInsert.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar stmt.");}
            try { con.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar conexão.");}
        }
        
        return pedido;
    }
    
    /**
     * Método para listar os itemPedidos
     * @param pedido
     * @return itemPedido - um objeto Cliente formado pelo registro itemPedido do banco de dados
     * @throws SQLException 
     */
    public List<ItemDoPedido> selectListaItensPedido(Pedido pedido) throws SQLException {

       Connection con = null;
       PreparedStatement pstmtSelect = null;
       ResultSet rs = null;
       List<ItemDoPedido> itensPedido = new ArrayList();
       
        try {
            con = new ConnectionFactory().getConnection();

            pstmtSelect = con.prepareStatement(selectListaItensPedido);

            // @DEGUB
            System.out.println(pstmtSelect);

            pstmtSelect.setInt(1, pedido.getId());

            // @DEGUB
            System.out.println(pstmtSelect);

            rs = pstmtSelect.executeQuery();

            while (rs.next()) {
                
                Produto produto = new Produto(
                    rs.getInt("id_produto"),
                    rs.getString("descricao")
                ); 
                ItemDoPedido itemPedido = new ItemDoPedido(
                    produto,
                    rs.getInt("qtdade")
                );
                itensPedido.add(itemPedido);
             }
           
        } catch (SQLException e) {
            System.out.println("Erro operações itemPedido: select by id");
        } finally {
           try {rs.close();} catch (SQLException ex) {throw new RuntimeException("Ocorreu erro ao fechar o ResultSet.");}
           try { pstmtSelect.close(); } catch (SQLException e) {throw new RuntimeException("Ocorreu erro ao fechar o PreparedStatement.");}
           try { con.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar a conexão com o banco de dados.");}
        }
       return itensPedido;
   }
   
   /**
    * Método para listar os pedido do cliente
    * @param List<Pedido> pedidosClientes - um lista de pedidos
    * @return
    * @throws SQLException 
    */
   public List<Pedido> selectListaPedido(Cliente cliente) throws SQLException {

       Connection con = null;
       PreparedStatement pstmtSelect = null;
       ResultSet rs = null;
       List<Pedido> pedidosCliente = new ArrayList();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       
        try {
            con = new ConnectionFactory().getConnection();

            pstmtSelect = con.prepareStatement(selectListaPedido);

            // @DEGUB
            System.out.println(pstmtSelect);

            pstmtSelect.setInt(1, cliente.getId());

            // @DEGUB
            System.out.println(pstmtSelect);

            rs = pstmtSelect.executeQuery();
            
            
            while (rs.next()) {
                java.sql.Timestamp timestamp = rs.getTimestamp("data");
                LocalDateTime data_pedido = rs.getTimestamp("data").toLocalDateTime();
                
                // @DEBUG
                System.out.println(data_pedido.format(formatter));
                System.out.println(cliente.getNome());
            
                Pedido pedido = new Pedido(
                    rs.getInt("id"),
                    data_pedido,
                    cliente,
                    null
                ); 
                pedidosCliente.add(pedido);
                
             }
           
        } catch (SQLException e) {
            System.out.println("Erro operações lista de pedido por cliente: select by id");
        } finally {
           try {rs.close();} catch (SQLException e) {  
                System.out.println(e.getMessage());
                throw new RuntimeException("Ocorreu erro ao fechar o ResultSet.");
            }
           try { pstmtSelect.close(); } catch (SQLException e) {throw new RuntimeException("Ocorreu erro ao fechar o PreparedStatement.");}
           try { con.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar a conexão com o banco de dados.");}
        }
        return pedidosCliente;
    }
   
   /**
     * Método para deletar pedido por id
     * @return itemPedido - um objeto Cliente formado pelo registro itemPedido do banco de dados
     * @throws SQLException 
     */
    public Pedido deletePedido(Pedido pedido) throws SQLException {

       Connection con = null;
       PreparedStatement pstmtDelete = null;
       
        try {
            con = new ConnectionFactory().getConnection();

            pstmtDelete = con.prepareStatement(deletePedido);
            
            // deletando itens do pedido para deletar o pedido;
            ItemDoPedidoDAO operacoesItensPedidoDAO = new ItemDoPedidoDAO();
            
            operacoesItensPedidoDAO.deleteItensPedido(pedido);
            
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
            System.out.println("Erro operações itemPedido: delete pedido by id");
        } finally {
           try { pstmtDelete.close(); } catch (SQLException e) {throw new RuntimeException("Ocorreu erro ao fechar o PreparedStatement.");}
           try { con.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar a conexão com o banco de dados.");}
        }
       return pedido;
   }
}
