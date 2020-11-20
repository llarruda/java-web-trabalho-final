/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dao.ProdutoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Produto;

/**
 *
 * @author llarruda
 */
public class ProdutoFacade {
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    
    public void inserir(Produto produto) {
        try {
            produtoDAO.insertProduto(produto);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Produto> listarProdutos() {
        List<Produto> produtoLista = null;
        
        try {
            produtoLista = produtoDAO.selectListaProduto();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return produtoLista;
    }
    
    public Produto selectProdById(int id) {
        Produto produto = null;
        
        try {
            produto = produtoDAO.selectProduto(id);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return produto;
    }
    
    public void atualizar(Produto produto) {
        try {
            produtoDAO.updateProduto(produto);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
/*    public int quantidade() {
        int qnt = 0;
        try {
            qnt = produtoDAO.countProdutos();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return qnt;
    }
*/
    public void deletar(int id) {
        try {
            produtoDAO.deleteProduto(id);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
