/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao;

import com.ufpr.model.db.DB;
import com.ufpr.model.dao.implement.CustomerDaoJDBC;
import com.ufpr.model.dao.implement.ItemDaoJDBC;
import com.ufpr.model.dao.implement.OrderDaoJDBC;
import com.ufpr.model.dao.implement.OrderItemDaoJDBC;

/**
 *
 * @author Jordi.Santos
 */
public class DaoFactory {
	
	public static OrderDao createOrderDao() {
		return new OrderDaoJDBC(DB.getConnection());
	}
	
	public static ItemDao createItemDao() {
		return new ItemDaoJDBC(DB.getConnection());
	}
        
        public static OrderItemDao createOrderItemDao(){
            return new OrderItemDaoJDBC(DB.getConnection());
        }
        
        public static CustomerDao createCustomerDao(){
            return new CustomerDaoJDBC(DB.getConnection());
        }
}
