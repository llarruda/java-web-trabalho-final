/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao;

import com.ufpr.model.entities.Order;
import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public interface OrderDao {
        void insert(Order obj);
	void update(Order obj);
	void deleteById(Integer id);
	Order findById(Integer id);
	List<Order> findAll();    
}
