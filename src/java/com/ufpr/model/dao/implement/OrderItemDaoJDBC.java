/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao.implement;

import com.ufpr.model.dao.OrderItemDao;
import com.ufpr.model.entities.OrderItem;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public class OrderItemDaoJDBC implements OrderItemDao{
    
    private Connection conn;

    public OrderItemDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(OrderItem obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(OrderItem obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrderItem> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
