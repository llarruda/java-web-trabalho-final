/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.control.servlet;

import com.ufpr.model.dao.ClienteDao;
import com.ufpr.model.dao.DaoFactory;

/**
 *
 * @author Jordi.Santos
 */
public class ConsoleCheck {
    public static void main (String [] args){
        
        ClienteDao cd = DaoFactory.createCustomerDao();
        
    }
    
}
