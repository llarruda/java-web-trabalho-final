/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.ItemDoPedido;
import model.Pedido;
import model.Produto;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author llarruda
 */
public class ItemDoPedidoFacadeTest {
    
    public ItemDoPedidoFacadeTest() {
    }

    /**
     * Test of inserir method, of class ItemDoPedidoFacade.
     */
    @Test
    public void testInserir() {
        System.out.println("inserir");
        
        Cliente cli = new Cliente(42, "Platão", "O Único", "11111111111");
        
        Produto prod1 = new Produto(50, "Papel A4");
        Produto prod2 = new Produto(52, "Tinta Guache");
        
        LocalDateTime dataPedido = LocalDateTime.now();
        
        ItemDoPedido itemPedido1 = new ItemDoPedido(prod1, 2);
        ItemDoPedido itemPedido2 = new ItemDoPedido(prod2, 1);
        
        List<ItemDoPedido> itensPedidoList = new ArrayList<ItemDoPedido>() {
            { add(itemPedido1); add(itemPedido2); }
        };
        
        Pedido pedido = new Pedido(66, dataPedido, cli, itensPedidoList);
        ItemDoPedidoFacade itemPedidoFacade = new ItemDoPedidoFacade();
        itemPedidoFacade.inserir(itemPedido1, pedido);
        itemPedidoFacade.inserir(itemPedido2, pedido);
    }
    
}
