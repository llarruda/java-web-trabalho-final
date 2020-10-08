/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao;

import com.ufpr.model.entities.Customer;
import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public interface CustomerDao {
        void insert(Customer obj);
	void update(Customer obj);
	void deleteById(Integer id);
	Customer findById(Integer id);
	List<Customer> findAll();
}
