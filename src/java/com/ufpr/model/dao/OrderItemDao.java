/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao;

import com.ufpr.model.entities.OrderItem;
import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public interface OrderItemDao {
    void insert(OrderItem obj);
    void update(OrderItem obj);
    List<OrderItem> findAll();
    
}
