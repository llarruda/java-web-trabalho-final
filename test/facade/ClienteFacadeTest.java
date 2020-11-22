/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.List;
import model.Cliente;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author llarruda
 */
public class ClienteFacadeTest {

    /**
     * Test of inserir method, of class ClienteFacade.
     */
    @Test
    public void testInserir() {
        System.out.println("inserir");
        Cliente cliente = null;
        ClienteFacade instance = new ClienteFacade();
        instance.inserir(cliente);
        fail("The test case is a prototype.");
    }

    /**
     * Test of listar method, of class ClienteFacade.
     */
    @Test
    public void testListar() {
        System.out.println("listar");
        ClienteFacade instance = new ClienteFacade();
        List expResult = null;
        List result = instance.listar();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscar method, of class ClienteFacade.
     */
    @Test
    public void testBuscar() {
        System.out.println("buscar");
        int id = 0;
        ClienteFacade instance = new ClienteFacade();
        Cliente expResult = null;
        Cliente result = instance.buscar(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorCpf method, of class ClienteFacade.
     */
    @Test
    public void testBuscarPorCpf() {
        System.out.println("buscarPorCpf");
        String cpf = "99999999993";
        ClienteFacade instance = new ClienteFacade();
        Cliente cliente = instance.buscarPorCpf(cpf);
        System.out.println(cliente.getNome());
    }

    /**
     * Test of atualizar method, of class ClienteFacade.
     */
    @Test
    public void testAtualizar() {
        System.out.println("atualizar");
        Cliente cliente = null;
        ClienteFacade instance = new ClienteFacade();
        instance.atualizar(cliente);
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidade method, of class ClienteFacade.
     */
    @Test
    public void testQuantidade() {
        System.out.println("quantidade");
        ClienteFacade instance = new ClienteFacade();
        int expResult = 0;
        int result = instance.quantidade();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletar method, of class ClienteFacade.
     */
    @Test
    public void testDeletar() {
        System.out.println("deletar");
        int id = 0;
        ClienteFacade instance = new ClienteFacade();
        instance.deletar(id);
        fail("The test case is a prototype.");
    }
    
}
