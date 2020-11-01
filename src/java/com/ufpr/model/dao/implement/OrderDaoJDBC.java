/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao.implement;


import com.ufpr.model.dao.OrderDao;
import com.ufpr.model.entities.Order;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public class OrderDaoJDBC implements OrderDao{
    
    private Connection conn;

    public OrderDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    public OrderDaoJDBC(){}

    @Override
    public void insert(Order obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Order obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
