/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.facade;

import com.ufpr.model.dao.implement.ProdutoDaoJDBC;
import com.ufpr.model.db.DbException;
import com.ufpr.model.entities.Produto;
import java.util.List;
import com.ufpr.model.dao.ProdutoDao;

/**
 *
 * @author Jordi.Santos
 */
public class ProdutoFacade {
    private ProdutoDao pd = new ProdutoDaoJDBC();

    public void insert(Produto obj) {
        try{
            pd.insert(obj);
        } catch (DbException e){}
    }

    public void update(Produto obj) {
        try{
            pd.update(obj);
        } catch (DbException e){}
    }

    public void deleteById(Integer id) {
        try{
            pd.deleteById(id);
        } catch (DbException e){}
    }

    public Produto findById(Integer id) {
        try{
            Produto pr = pd.findById(id);
        } catch (DbException e){}
    }

    public List<Produto> findAll() {
        try{
            pd.findAll();
        } catch (DbException e) {}
    }
    
}
