/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.facade;

import com.ufpr.model.dao.OrderDao;
import com.ufpr.model.dao.implement.OrderDaoJDBC;
import com.ufpr.model.db.DbException;
import com.ufpr.model.entities.Order;

/**
 *
 * @author Jordi.Santos
 */
public class OrderFacade {
    private OrderDao od = new OrderDaoJDBC();

    public OrderFacade() {
    }
    
    public void insert(Order order){
        try{
            od.insert(order);
        } catch (DbException e){}
    }
    
    public void update(Order order){
        try{
            od.update(order);
        } catch (DbException e){}
    }
        
    public void deleteById(Order order){
        try{
            od.deleteById(order.getId());
        }catch (DbException e){}
    }
    
    public void findById(Order order){
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
