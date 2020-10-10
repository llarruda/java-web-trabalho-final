/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.facade;

import com.ufpr.model.dao.CustomerDao;
import com.ufpr.model.dao.implement.CustomerDaoJDBC;
import com.ufpr.model.entities.Customer;

/**
 *
 * @author Jordi.Santos
 */
public class CustomerFacade {
    private CustomerDao cd = new CustomerDaoJDBC();

    public CustomerFacade() {
    }
    
    public void insert(Customer customer){
        cd.insert(customer);
    }
    
    public void update(Customer customer){
        cd.update(customer);
    }
        
    public void deleteById(Customer customer){
        cd.deleteById(customer.getId());
    }
    
    public void findById(Customer customer){
        cd.findById(customer.getId());
    }
    
    public void FindAll(){
        cd.findAll();
    }
}
