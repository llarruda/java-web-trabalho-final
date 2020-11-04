/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao.implement;


import com.ufpr.model.entities.Pedido;
import java.sql.Connection;
import java.util.List;
import com.ufpr.model.dao.PedidoDao;

/**
 *
 * @author Jordi.Santos
 */
public class PedidoDaoJDBC implements PedidoDao{
    
    private Connection conn;

    public PedidoDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    public PedidoDaoJDBC(){}

    @Override
    public void insert(Pedido obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Pedido obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pedido findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pedido> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
