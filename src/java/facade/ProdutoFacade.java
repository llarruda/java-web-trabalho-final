/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dao.ProdutoDao;
import db.DbException;
import java.util.List;
import model.Produto;


/**
 *
 * @author Jordi.Santos
 */
public class ProdutoFacade {
    private ProdutoDao pd = new ProdutoDao();

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
        Produto pr = null;
        try{
            pr = pd.findById(id);
        } catch (DbException e){}
        return pr;
    }

    public List<Produto> findAll() {
        List<Produto> lpd = null;
        try{
           lpd = pd.findAll();
        } catch (DbException e) {}
        return lpd;
    }
    
}
