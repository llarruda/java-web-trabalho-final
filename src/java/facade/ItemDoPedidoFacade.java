/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dao.ItemDoPedidoDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ItemDoPedido;
import model.Pedido;

/**
 *
 * @author llarruda
 */
public class ItemDoPedidoFacade {
    private ItemDoPedidoDAO itemDoPedidoDAO = new ItemDoPedidoDAO();
    
    public void inserir(ItemDoPedido itemPedido, Pedido pedido) {
        try {
            itemDoPedidoDAO.insertItemPedido(itemPedido, pedido);
        } catch (SQLException ex) {
            Logger.getLogger(ItemDoPedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletar(Pedido pedido) {
        try {
            itemDoPedidoDAO.deleteItensPedido(pedido);
        } catch (SQLException ex) {
            Logger.getLogger(ItemDoPedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
