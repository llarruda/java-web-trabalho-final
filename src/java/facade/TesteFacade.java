/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dao.ClienteDAO;
import model.Cliente;

/**
 *
 * @author Junior
 */
public class TesteFacade {
    public static void main(String[] agrs){
        ClienteFacade cf = new ClienteFacade();
        
        System.out.println("quantidade de clientes = " + cf.quantidade());

//        Cliente cli = new Cliente(0, "12233355599", "jose", "silva");
//        cf.inserir(cli);
//        
//        ClienteDAO cc = new ClienteDAO();
//        
//        cf.inserir(cli);
    }
}
