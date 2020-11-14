/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionFactory;
import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import model.Cliente;
import model.Produto;

/**
 *
 * @author Jordi.Santos
 */
public class ProdutoDao{

    private final String stmtListar = "SELECT * FROM produto";
    
    private Connection conn;

    public ProdutoDao(Connection conn) {
        this.conn = conn;
    }
    
    public ProdutoDao(){}

    public void insert(Produto obj) {
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                            "INSERT INTO produto"
                            + "(descricao)"
                            + "VALUES "
                            + "(?)",
                            Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getDescricao());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                    ResultSet rs = st.getGeneratedKeys();
                    if (rs.next()) {
                            int id = rs.getInt(1);
                            obj.setId(id);
                    }
            } else {
                    throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
                throw new DbException(e.getMessage());
        }
        finally {
                DB.closeStatement(st);
                DB.closeConnection();
        }
    }

    public void update(Produto obj) {
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                            "UPDATE produto "
                            + "SET descricao = ? "
                            + "WHERE Id = ?");
            st.setString(1, obj.getDescricao());
            st.setInt(4, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("DELETE FROM produto WHERE Id = ?");
            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
                throw new DbException (e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public Produto findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        Produto resultItem;
        
        try{
            conn = DB.getConnection();
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
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }
    }

    public List<Produto> findAll() {
/*        PreparedStatement st = null;
        ResultSet rs = null;
        
        List<Produto> list = new ArrayList();
        
        try{
            conn = DB.getConnection();
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
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        } 
    }*/
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Produto> lista = new ArrayList();
        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            while(rs.next()){
                Produto produto = new Produto(rs.getInt("id"), rs.getString("descricao"));
                produto.setId(rs.getInt("id"));
                lista.add(produto);
            }
            return lista;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar clientes no banco de dados. " + ex.getMessage());
        } finally{
            try{rs.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar result set: " + ex.getMessage());}
            try{stmt.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar stmt: " + ex.getMessage());}
            try{conn.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar conexao: " + ex.getMessage());}
        }    
    }
}
