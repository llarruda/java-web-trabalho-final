/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.control;

import com.ufpr.model.dao.CustomerDao;
import com.ufpr.model.dao.implement.CustomerDaoJDBC;
import com.ufpr.model.entities.Customer;
import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public class App {
    public static void main(String [] args){
    
    CustomerDao cd = new CustomerDaoJDBC();

    
    List<Customer> listC = cd.findAll();
    for (Customer c : listC){
        System.out.println(c);
    }
    
    }
}
