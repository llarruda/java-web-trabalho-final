/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import model.Produto;

/**
 *
 * @author llarruda
 */
public class ProdutoFacadeTest {
    
    @Test
    public void inseirTest() {
        ProdutoFacade produtoFacade = new ProdutoFacade();
        
        Produto produto = new Produto(1, "Caneta Bic Azul");
        
        produtoFacade.inserir(produto);
    }
    
    @Test
    public void listarProdutosTest() {
        List<Produto> produtoLista;
        
        ProdutoFacade produtoFacade = new ProdutoFacade();
        
        produtoLista = produtoFacade.listarProdutos();
        
        System.out.println(">>>>"  + produtoLista.get(2).getDescricao());
    }
    
     @Test
    public void searchProdutoByDesc() {
        List<Produto> produtoLista;
        
        ProdutoFacade produtoFacade = new ProdutoFacade();
        
        produtoLista = produtoFacade.searchProdutoByDesc("Can");
        
        if (produtoLista == null) {
            System.err.println("Retornando nulo, produto não encontrado");
        } else {
            System.out.println(produtoLista.get(0).getDescricao());
        }
    }
}
