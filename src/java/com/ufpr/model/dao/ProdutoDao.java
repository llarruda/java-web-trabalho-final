/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.dao;

import com.ufpr.model.entities.Produto;
import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public interface ProdutoDao {
    	void insert(Produto obj);
	void update(Produto obj);
	void deleteById(Integer id);
	Produto findById(Integer id);
	List<Produto> findAll();
}
