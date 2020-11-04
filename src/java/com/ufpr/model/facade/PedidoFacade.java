/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.facade;

import com.ufpr.model.dao.implement.PedidoDaoJDBC;
import com.ufpr.model.db.DbException;
import com.ufpr.model.entities.Pedido;
import com.ufpr.model.dao.PedidoDao;

/**
 *
 * @author Jordi.Santos
 */
public class PedidoFacade {
    private PedidoDao od = new PedidoDaoJDBC();

    public PedidoFacade() {
    }
    
    public void insert(Pedido order){
        try{
            od.insert(order);
        } catch (DbException e){}
    }
    
    public void update(Pedido order){
        try{
            od.update(order);
        } catch (DbException e){}
    }
        
    public void deleteById(Pedido order){
        try{
            od.deleteById(order.getId());
        }catch (DbException e){}
    }
    
    public void findById(Pedido order){
        try{
            od.findById(order.getId());
        }catch (DbException e){}
    }
    
    public void findAll(){
        try{
            od.findAll();
        }catch (DbException e){}
    }
}
