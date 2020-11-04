/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao.implement;

import com.ufpr.model.db.DB;
import com.ufpr.model.db.DbException;
import com.ufpr.model.entities.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import com.ufpr.model.dao.ClienteDao;
import java.util.ArrayList;

/**
 *
 * @author Jordi.Santos
 */
public class ClienteDaoJDBC implements ClienteDao{
    
    private Connection conn;

    public ClienteDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    public ClienteDaoJDBC(){}

    @Override
    public void insert(Cliente obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                            "INSERT INTO customer"
                            + "(Name, Surename, cpf)"
                            + "VALUES "
                            + "(?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            st.setString(2, obj.getSobrenome());
            st.setString(3, obj.getCpf());

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
    public void update(Cliente obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                            "UPDATE seller "
                            + "SET Name = ?, Surename = ?, cpf = ? "
                            + "WHERE Id = ?");
            st.setString(1, obj.getName());
            st.setString(2, obj.getSobrenome());
            st.setString(3, obj.getCpf());
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
            st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
                throw new DbException (e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    @Override
    public Cliente findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("SELECT * FROM cliente WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if(rs.next()){
                Cliente cconsulta = new Cliente(rs.getInt("id"), rs.getString("cpf"), rs.getString("nome"), rs.getString("sobrenome"));
                
                return cconsulta;
            } else {
                throw new RuntimeException("Não existe cliente com o ID: " + id);
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }
    }

    @Override
    public List<Cliente> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Cliente> list = new ArrayList();
        
        try{
            st = conn.prepareStatement("SELECT * FROM cliente");
            rs = st.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("cpf"), rs.getString("nome"), rs.getString("sobrenome"));
                cliente.setId(rs.getInt("id"));
                
                list.add(cliente);
            }
            return list;
        } catch (SQLException e){
            throw new DbException (e.getMessage());
        } finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }
    }
    
}
