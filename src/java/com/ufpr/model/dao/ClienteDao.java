/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao;

import com.ufpr.model.entities.Cliente;
import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public interface ClienteDao {
        void insert(Cliente obj);
	void update(Cliente obj);
	void deleteById(Integer id);
	Cliente findById(Integer id);
	List<Cliente> findAll();
}
