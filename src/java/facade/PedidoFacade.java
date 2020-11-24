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
import model.ItemDoPedido;
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
        List<Pedido> listPedido = null;

        try {
            listPedido = pedidoDAO.selectPedidos();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        Pedido pedido = null;
        for(int i = 0; i < listPedido.size(); i++) {

            if(id == listPedido.get(i).getId()) {
                pedido = new Pedido(listPedido.get(i).getId(), listPedido.get(i).getData(), listPedido.get(i).getCliente(), null);
            }
            break;
        }
        
        return pedido;
    }
    
    public List<Pedido> listarPedidosCliente(Cliente cliente) {
        
        List<Pedido> pedidoLista = null;
        
        try {
            pedidoLista = pedidoDAO.selectPedidosPorCliente(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pedidoLista;
    }
    
            
    public List<Pedido> listarPedidosPorId(Pedido pedido) {
        
        List<Pedido> pedidoLista = null;
        
        try {
            pedidoLista = pedidoDAO.selectPedidosPorId(pedido);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pedidoLista;
    }
    
    public void deletar(Pedido pedido) {
        try {
            pedidoDAO.deletePedido(pedido);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<ItemDoPedido> listarItensPedido(Pedido pedido) {
        
        List<ItemDoPedido> itensLista = null;
        
        try {
            itensLista = pedidoDAO.selectListaItensPedido(pedido);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return itensLista;
    }
}
