/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dao.PedidoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Pedido;

/**
 *
 * @author llarruda
 */
public class PedidoFacade {
    private PedidoDAO pedidoDAO = new PedidoDAO();
    
    public void inserir(Pedido pedido) {
        try {
            pedidoDAO.insertPedido(pedido);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Pedido> listarPedidos() {
        List<Pedido> pedidoLista = null;
        
        try {
            pedidoLista = pedidoDAO.selectPedidos();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pedidoLista;
    }
    
    // TODO: implementar ação na classe PedidoDAO par permitir a busca de pedido por id
    public Pedido buscarPedido(int id) {
        Pedido pedido = null;
        
        return pedido;
    }
    
    public void deletar(Pedido pedido) {
        try {
            pedidoDAO.deletePedido(pedido);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
