/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dao.ItemDoPedidoDAO;
import dao.PedidoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ItemDoPedido;
import model.Pedido;
import model.Produto;

/**
 *
 * @author Jordi.Santos
 */
public class ItemDoPedidoFacade {
    private ItemDoPedidoDAO itemDao = new ItemDoPedidoDAO();
    
    public void insertItemPedido(ItemDoPedido item, Pedido pedido) {
        try {
            itemDao.insertItemPedido(item, pedido);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteItensPedido(Pedido pedido) {
        try {
            itemDao.deleteItensPedido(pedido);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
