/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.facade;

import com.ufpr.model.dao.ItemDao;
import com.ufpr.model.dao.implement.ItemDaoJDBC;
import com.ufpr.model.db.DbException;
import com.ufpr.model.entities.Item;
import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public class ItemFacade implements ItemDao {
    
    private ItemDao iDao = new ItemDaoJDBC();

    @Override
    public void insert(Item obj) {
        try{
            iDao.insert(obj);
        } catch (DbException e){}
    }

    @Override
    public void update(Item obj) {
        try{
            iDao.update(obj);
        } catch (DbException e){}
    }

    @Override
    public void deleteById(Integer id) {
        try{
            iDao.deleteById(id);
        } catch (DbException e){}
    }

    @Override
    public Item findById(Integer id) {
        try{
            Item resultItem = iDao.findById(id);
            return resultItem;
        } catch (DbException e){}
    }

    @Override
    public List<Item> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
