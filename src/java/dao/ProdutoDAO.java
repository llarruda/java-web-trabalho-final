/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionFactory;
import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author llarruda
 */
public class ProdutoDAO {
    private final String insertProduto = "INSERT INTO produto (descricao) VALUES (?);";
    private final String selectProduto = "SELECT id, descricao FROM produto WHERE id = ?;";
    private final String updateProduto = "UPDATE produto SET descricao = ? WHERE id = ?;";
    private final String deleteProduto = "DELETE FROM produto WHERE id = ? AND id NOT IN (SELECT id_produto FROM item_do_pedido);";
    private final String selectListaProduto = "SELECT id, descricao FROM produto LIMIT 8;";
    private final String countProduto = "SELECT COUNT(*) FROM produto";
    private final String searchProdutoByDesc = "SELECT id, descricao FROM produto WHERE descricao LIKE ?;";
    
    /**
    * Método para inserir um produto
    * @param produto - um objeto do tipo produto
    * return void
    * @throws SQLException 
    */
    public void insertProduto(Produto produto) throws SQLException {
       
        Connection con = null;
        PreparedStatement pstmtInsert = null;
        
        try {
            con = new ConnectionFactory().getConnection();
            
            pstmtInsert = con.prepareStatement(insertProduto, Statement.RETURN_GENERATED_KEYS);
            // @DEGUB
            System.out.println(pstmtInsert);
            
            pstmtInsert.setString(1, produto.getDescricao());
            
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
            produto.setId(i);
            
            // @DEGUB
            System.out.println("Id utilizado: " + produto.getId());
            
        } catch (SQLException e) {
            System.out.println("Erro operações produto: insert");
        } finally {
            try { pstmtInsert.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar stmt.");}
            try { con.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar conexão.");}
        }  
    }
    
    /**
     * Método para listar o produto por id
     * @return produto - um objeto produto formado pelo registro produto do banco de dados
     * @throws SQLException 
     */
    public Produto selectProduto(int id) throws SQLException {

       Connection con = null;
       PreparedStatement pstmtSelect = null;
       ResultSet rs = null;
       Produto produto = null;
       
        try {
           con = new ConnectionFactory().getConnection();

           pstmtSelect = con.prepareStatement(selectProduto);
           
           // @DEGUB
           System.out.println(pstmtSelect);
           
           pstmtSelect.setInt(1, id);
           
           // @DEGUB
           System.out.println(pstmtSelect);

           rs = pstmtSelect.executeQuery();

           if (rs.next()) {
              produto = new Produto(
                   rs.getInt("id"),
                   rs.getString("descricao")
               );
            } else {
               produto = new Produto(0, null);
               throw new RuntimeException("Id inválido= "+ id);
            }
           
        } catch (SQLException e) {
            System.out.println("Erro operações produto: select by id");
        } finally {
           try {rs.close();} catch (SQLException ex) {throw new RuntimeException("Ocorreu erro ao fechar o ResultSet.");}
           try { pstmtSelect.close(); } catch (SQLException e) {throw new RuntimeException("Ocorreu erro ao fechar o PreparedStatement.");}
           try { con.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar a conexão com o banco de dados.");}
        }
       return produto;
   }
    
    /**
    * Método para atualizar um produto específico
    * @param produto- um objeto do tipo produto
    * return void
    * @throws SQLException 
    */
    public void updateProduto(Produto produto) throws SQLException {

       Connection con = null;
       PreparedStatement pstmtUpdate = null;

       try {
           con = new ConnectionFactory().getConnection();

           pstmtUpdate = con.prepareStatement(updateProduto);
           
           // @DEGUB
           System.out.println(pstmtUpdate);

           pstmtUpdate.setString(1, produto.getDescricao());
           pstmtUpdate.setInt(2, produto.getId());

           // @DEGUB
           System.out.println(pstmtUpdate);

           pstmtUpdate.executeUpdate();

           // @DEGUB
           System.out.println("Passou do execute");

           // faz o commit da transação DML
           con.commit();

           // @DEBUG
           System.out.println("Dados registrados com sucesso.");

       } catch (SQLException e) {
               System.out.println("Erro operações produto: update");
       } finally {
           try { pstmtUpdate.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar stmt.");}
           try { con.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar conexão.");}
       }
    }
   
    /**
     * Método excluir um produto específico
     * @param produto- um objeto do tipo produto
     * @return quantidadde de produtos deletados
     * @throws SQLException 
     */
    public int deleteProduto(Produto produto) throws SQLException {
       
        Connection con = null;
        PreparedStatement pstmtDelete = null;
        int itensDeletados= 0;
        
        try {
            con = new ConnectionFactory().getConnection();
            
            pstmtDelete = con.prepareStatement(deleteProduto);
            
            // @DEGUB
            System.out.println(pstmtDelete);
            
            pstmtDelete.setInt(1, produto.getId());
            
            // @DEGUB
            System.out.println(pstmtDelete);
            
            pstmtDelete.execute();
            
            itensDeletados = pstmtDelete.getUpdateCount();
            System.out.println("Quantidade de produtos deltados: " + itensDeletados);
            
            // @DEGUB
            System.out.println("Passou do execute");
            
            // faz o commit da transação DML
            con.commit();
            
            // @DEBUG
            System.out.print("Commit da operação.");
           
            
        } catch (SQLException e) {
                System.out.println("Erro operações produto: delete by id");
        } finally {
            try { pstmtDelete.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar stmt.");}
            try { con.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar conexão.");}
        }  
        return itensDeletados;
    }
    
    /**
     * Método para listar todos os produtos
     * @return produtos - um lista de produtos List<Produto>
     * @throws SQLException 
     */
    public List<Produto> selectListaProduto() throws SQLException {

       Connection con = null;
       PreparedStatement pstmtSelect = null;
       ResultSet rs = null;
       List<Produto> produtos = new ArrayList();
       
        try {
            con = new ConnectionFactory().getConnection();

                pstmtSelect = con.prepareStatement(selectListaProduto);
                
                // @DEGUB
                System.out.println(pstmtSelect);

                rs = pstmtSelect.executeQuery();
                
                //produtos = new ArrayList();

                while (rs.next()) {
                    Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("descricao")
                    );
                    produtos.add(produto);
                }

        } catch (SQLException e) {
            System.out.println("Erro operações produto: select all");
        } finally {
            try {rs.close();} catch (SQLException ex) {throw new RuntimeException("Ocorreu erro ao fechar o ResultSet.");}
            try { pstmtSelect.close(); } catch (SQLException e) {throw new RuntimeException("Ocorreu erro ao fechar o PreparedStatement.");}
            try { con.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar a conexão com o banco de dados.");}
        }
        return produtos;
    }
    
    /**
     * Método para contar os produtos
     * @return int quantidadeProdutos 
     * @throws SQLException 
     */
    public int countProdutos() throws SQLException {

       Connection con = null;
       PreparedStatement pstmtSelect = null;
       ResultSet rs = null;
       int quantidadeProdutos = 0;
       
        try {
            con = new ConnectionFactory().getConnection();

                pstmtSelect = con.prepareStatement(selectListaProduto);
                
                // @DEGUB
                System.out.println(pstmtSelect);

                rs = pstmtSelect.executeQuery();
                
                rs.next();
                
                quantidadeProdutos = rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("Erro operações produto: select all");
        } finally {
            try {rs.close();} catch (SQLException ex) {throw new RuntimeException("Ocorreu erro ao fechar o ResultSet.");}
            try { pstmtSelect.close(); } catch (SQLException e) {throw new RuntimeException("Ocorreu erro ao fechar o PreparedStatement.");}
            try { con.close(); } catch (SQLException e) {throw new RuntimeException("Falha ao fechar a conexão com o banco de dados.");}
        }
        return quantidadeProdutos;
    }
    
    public List<Produto> searchProdutoByDesc(String descricao) {
        Connection conn = null;
        PreparedStatement pstmtSelect = null;
        ResultSet rs = null;
        List<Produto> produtosRetornados = new ArrayList();
        
        try{
            conn = ConnectionFactory.getConnection();
            pstmtSelect = conn.prepareStatement(searchProdutoByDesc);
            
            // @DEGUB
            System.out.println(pstmtSelect);
            
            
            pstmtSelect.setString(1, "%" + descricao + "%");
            
            // @DEGUB
            System.out.println(pstmtSelect);
            
            rs = pstmtSelect.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("descricao")
                );
                
                produtosRetornados.add(produto);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar produtos no banco de dados por descrição. " + ex.getMessage());
        } catch(Exception ex) {
            return null;
        }
            finally{
            try{rs.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar result set: " + ex.getMessage());}
            try{pstmtSelect.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar stmt: " + ex.getMessage());}
            try{conn.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar conexao: " + ex.getMessage());}
        }
        
        return produtosRetornados;
    }
}
