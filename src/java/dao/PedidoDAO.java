/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionFactory;
import connection.DbException;
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
    private String insertPedido = "INSERT INTO pedido (data, id_cliente) VALUES (?, ?);";
    private String selecPedido = "SELECT data, id_cliente FROM pedido WHERE id_cliente = ? ";
    private String updatePedido = "UPDATE pedido set data = ?, id_cliente = ? WHERE id_cliente = ?";
    private String deletePedido = "DELETE FROM pedido WHERE id = ?;";
    private String selectListaPedido = "SELECT id, data, id_cliente FROM pedido WHERE id_cliente = ?;";
    private String selectListaItensPedido = "SELECT i.id_item, i.id_pedido, i.id_produto, prod.descricao, i.qtdade FROM item_do_pedido AS i\n" +
                                            "INNER JOIN produto AS prod ON i.id_produto = prod.id\n" +
                                            "INNER JOIN pedido as p ON p.id = i.id_pedido\n" +
                                            "WHERE p.id = ?;";
    
    private Connection conn = null;
    
    public PedidoDAO() {
    }
    
    /**
    * Método para inserir um Pedido
    * @param itensPedido - uma lista de produtos selecionados para o pedido
    * return void
    * @throws SQLException 
    */
    public void insertPedido(Pedido pedido) throws SQLException {
        PreparedStatement st = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(insertPedido,
                            Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, pedido.getId());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                    ResultSet rs = st.getGeneratedKeys();
                    if (rs.next()) {
                            int id = rs.getInt(1);
                            pedido.setId(id);
                    }
            } else {
                    throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
                throw new DbException(e.getMessage());
        }
        finally {
                ConnectionFactory.closeStatement(st);
                ConnectionFactory.closeConnection();
        }
    }
    
    /**
     * Método para listar os itemPedidos
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
           ConnectionFactory.closeStatement(pstmtSelect);
           ConnectionFactory.closeResultSet(rs);
           ConnectionFactory.closeConnection();
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
