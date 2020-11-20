/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dao.ClienteDAO;
import java.util.List;
import model.Cliente;
import model.Produto;

/**
 *
 * @author Junior
 */
public class TesteFacade {
    public static void main(String[] agrs){
        ProdutoFacade pf = new ProdutoFacade();
        
        List<Produto>lista = pf.listarProdutos();
        
        for (Produto p : lista){
        System.out.println(p.toString());
        }
//        Cliente cli = new Cliente(0, "12233355599", "jose", "silva");
//        cf.inserir(cli);
//        
//        ClienteDAO cc = new ClienteDAO();
//        
//        cf.inserir(cli);
    }
}
