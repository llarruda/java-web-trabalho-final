/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao.implement;

import com.ufpr.model.entities.ItemDePedido;
import java.sql.Connection;
import java.util.List;
import com.ufpr.model.dao.ItemDePedidoDao;

/**
 *
 * @author Jordi.Santos
 */
public class ItemDePedidoDaoJDBC implements ItemDePedidoDao{
    
    private Connection conn;

    public ItemDePedidoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(ItemDePedido obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ItemDePedido obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemDePedido> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
