/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao.implement;

import com.ufpr.model.dao.ItemDao;

import com.ufpr.model.db.DB;
import com.ufpr.model.db.DbException;

import com.ufpr.model.entities.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public class ItemDaoJDBC implements ItemDao{
    
    private Connection conn;

    public ItemDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Item obj) {
        conn = DB.getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                            "INSERT INTO produto"
                            + "(descricao)"
                            + "VALUES "
                            + "(?)",
                            Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getDescription());

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
    public void update(Item obj) {
        conn = DB.getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                            "UPDATE produto "
                            + "SET descricao = ? "
                            + "WHERE Id = ?");
            st.setString(1, obj.getDescription());
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
        conn = DB.getConnection();
        PreparedStatement st = null;

        try {
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
    public Item findById(Integer id) {
        conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        Item resultItem;
        
        try{
            st = conn.prepareStatement("SELECT * FROM produto WHERE Id = ?");
            st.setInt(1, id);
            
            rs = st.executeQuery();
            
            if(rs.next()){
                resultItem = new Item(rs.getInt("id"), rs.getString("descricao"));
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
    public List<Item> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
