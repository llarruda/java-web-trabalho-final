/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionFactory;
import connection.DbException;
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
    
    private Connection conn;

    public ProdutoDAO(Connection conn) {
        this.conn = conn;
    }

    public ProdutoDAO() {
    }

    public void insertProduto(Produto produto) throws SQLException {
        PreparedStatement st = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(
                            "INSERT INTO produto"
                            + "(descricao)"
                            + "VALUES "
                            + "(?)",
                            Statement.RETURN_GENERATED_KEYS);
            st.setString(1, produto.getDescricao());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                    ResultSet rs = st.getGeneratedKeys();
                    if (rs.next()) {
                            int id = rs.getInt(1);
                            produto.setId(id);
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
     * Método para listar o produto por id
     * @return produto - um objeto produto formado pelo registro produto do banco de dados
     * @throws SQLException 
     */
    public Produto selectProduto(int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        Produto resultItem;
        
        try{
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM produto WHERE Id = ?");
            st.setInt(1, id);
            
            rs = st.executeQuery();
            
            if(rs.next()){
                resultItem = new Produto(rs.getInt("id"), rs.getString("descricao"));
                return resultItem;
            } else {
                throw new DbException("There is no product matching the id " + id);
            }
        } catch (SQLException e){
            throw new DbException (e.getMessage());
        } finally {
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeResultSet(rs);
            ConnectionFactory.closeConnection();
        }
   }
    
    /**
    * Método para atualizar um produto específico
    * @param produto- um objeto do tipo produto
    * return void
    * @throws SQLException 
    */
    public void updateProduto(Produto produto) throws SQLException {
        PreparedStatement st = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(
                            "UPDATE produto "
                            + "SET descricao = ? "
                            + "WHERE Id = ?");
            st.setString(1, produto.getDescricao());
            st.setInt(4, produto.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeConnection();
        }
    }
   
    /**
     * Método excluir um produto específico
     * @param produto- um objeto do tipo produto
     * return void
     * @throws SQLException 
     */
    public void deleteProduto(int id) throws SQLException {
        PreparedStatement st = null;

        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("DELETE FROM produto WHERE Id = ?");
            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
                throw new DbException (e.getMessage());
        } finally {
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeConnection();
        }
    }
    
    /**
     * Método para listar todos os produtos
     * @return produtos - um lista de produtos List<Produto>
     * @throws SQLException 
     */
    public List<Produto> selectListaProduto() throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        List<Produto> list = new ArrayList();
        
        try{
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM produto");
            rs = st.executeQuery();
            
            while(rs.next()){
                Produto item = new Produto(rs.getInt("id"), rs.getString("descricao"));
                item.setId(rs.getInt("id"));
                list.add(item);
            }
            return list;
        } catch (SQLException e){
            throw new DbException (e.getMessage());
        } finally {
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeResultSet(rs);
            ConnectionFactory.closeConnection();
        } 
    }
    
    /**
     * Método para contar os produtos
     * @return int quantidadeProdutos 
     * @throws SQLException 
     *
    public int countProdutos() throws SQLException {

       Connection con = null;
       PreparedStatement pstmtSelect = null;
       ResultSet rs = null;
       int quantidadeProdutos = 0;
       
        try {
            conn = ConnectionFactory.getConnection();

                pstmtSelect = con.prepareStatement("COUNT");
                
                // @DEGUB
                System.out.println(pstmtSelect);

                rs = pstmtSelect.executeQuery();
                
                rs.next();
                
                quantidadeProdutos = rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("Erro operações produto: select all");
        } finally {
            ConnectionFactory.closeStatement(pstmtSelect);
            ConnectionFactory.closeResultSet(rs);
            ConnectionFactory.closeConnection();
        }
        return quantidadeProdutos;
    }*/
}