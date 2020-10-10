/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao.implement;

import com.ufpr.model.db.DB;
import com.ufpr.model.db.DbException;
import com.ufpr.model.dao.CustomerDao;
import com.ufpr.model.entities.Customer;
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
public class CustomerDaoJDBC implements CustomerDao{
    
    private Connection conn;

    public CustomerDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    public CustomerDaoJDBC(){}

    @Override
    public void insert(Customer obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                            "INSERT INTO customer"
                            + "(Name, Surename, cpf)"
                            + "VALUES "
                            + "(?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            st.setString(2, obj.getSurename());
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
        }
    }

    @Override
    public void update(Customer obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                            "UPDATE seller "
                            + "SET Name = ?, Surename = ?, cpf = ? "
                            + "WHERE Id = ?");
            st.setString(1, obj.getName());
            st.setString(2, obj.getSurename());
            st.setString(3, obj.getCpf());
            st.setInt(4, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
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
        }
    }

    @Override
    public Customer findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
