/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao;

import com.ufpr.model.entities.Item;
import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public interface ItemDao {
    	void insert(Item obj);
	void update(Item obj);
	void deleteById(Integer id);
	Item findById(Integer id);
	List<Item> findAll();
}
