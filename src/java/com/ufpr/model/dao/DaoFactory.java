/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao;

import com.ufpr.model.db.DB;
import com.ufpr.model.dao.implement.ClienteDaoJDBC;
import com.ufpr.model.dao.implement.ProdutoDaoJDBC;
import com.ufpr.model.dao.implement.PedidoDaoJDBC;
import com.ufpr.model.dao.implement.ItemDePedidoDaoJDBC;

/**
 *
 * @author Jordi.Santos
 */
public class DaoFactory {
	
	public static PedidoDao createOrderDao() {
		return new PedidoDaoJDBC(DB.getConnection());
	}
	
	public static ProdutoDao createItemDao() {
		return new ProdutoDaoJDBC(DB.getConnection());
	}
        
        public static ItemDePedidoDao createOrderItemDao(){
            return new ItemDePedidoDaoJDBC(DB.getConnection());
        }
        
        public static ClienteDao createCustomerDao(){
            return new ClienteDaoJDBC(DB.getConnection());
        }
}
