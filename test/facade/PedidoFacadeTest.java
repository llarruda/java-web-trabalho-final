/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

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
public class PedidoFacadeTest {

    /**
     * Test of inserir method, of class PedidoFacade.
     */
    @Test
    public void listarPedidos() {
        System.out.println("inserir");
        
        PedidoFacade pedidoFacade = new PedidoFacade();
        
        List<Pedido> pedidos = pedidoFacade.listarPedidos();
        
        System.out.println(pedidos.get(0).getData());
    }
}
