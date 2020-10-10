/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.facade;

import com.ufpr.model.dao.CustomerDao;
import com.ufpr.model.dao.implement.CustomerDaoJDBC;
import com.ufpr.model.db.DbException;
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
        try{
            cd.insert(customer);
        } catch (DbException e){}
    }
    
    public void update(Customer customer){
        try{
            cd.update(customer);
        } catch (DbException e){}
    }
        
    public void deleteById(Customer customer){
        try{
            cd.deleteById(customer.getId());
        }catch (DbException e){}
    }
    
    public void findById(Customer customer){
        try{
            cd.findById(customer.getId());
        }catch (DbException e){}
    }
    
    public void findAll(){
        try{
            cd.findAll();
        }catch (DbException e){}
    }
} //fim da classe
