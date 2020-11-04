/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao;

import com.ufpr.model.entities.Pedido;
import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public interface PedidoDao {
        void insert(Pedido obj);
	void update(Pedido obj);
	void deleteById(Integer id);
	Pedido findById(Integer id);
	List<Pedido> findAll();    
}
