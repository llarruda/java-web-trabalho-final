/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.facade;

import com.ufpr.model.dao.implement.ClienteDaoJDBC;
import com.ufpr.model.db.DbException;
import com.ufpr.model.entities.Cliente;
import com.ufpr.model.dao.ClienteDao;
import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public class ClienteFacade{
    private ClienteDao cd = new ClienteDaoJDBC();

    public ClienteFacade() {
    }
    
    public void insert(Cliente customer){
        try{
            cd.insert(customer);
        } catch (DbException e){}
    }
    
    public void update(Cliente customer){
        try{
            cd.update(customer);
        } catch (DbException e){}
    }

    public void deleteById(Integer id) {
        try{
            cd.deleteById(id);
        }catch (DbException e){}
    }

    public Cliente findById(Integer id) {
        try{
            cd.findById(id);
        }catch (DbException e){}
    }
        
    public List<Cliente> findAll(){
        try{
            cd.findAll();
        }catch (DbException e){}
    }
} //fim da classe
