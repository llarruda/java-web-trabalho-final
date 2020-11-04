/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao.implement;


import com.ufpr.model.db.DB;
import com.ufpr.model.db.DbException;

import com.ufpr.model.entities.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import com.ufpr.model.dao.ProdutoDao;

/**
 *
 * @author Jordi.Santos
 */
public class ProdutoDaoJDBC implements ProdutoDao{
    
    private Connection conn;

    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    public ProdutoDaoJDBC(){}

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public List<Produto> findAll() {
        PreparedStatement st = null;
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
    }
   
}
