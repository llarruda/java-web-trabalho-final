/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import connection.DbException;
import dao.PedidoDAO;
import java.sql.SQLException;
import model.Cliente;
import model.Pedido;

/**
 *
 * @author Jordi.Santos
 */
public class PedidoFacade {
    private PedidoDAO pd = new PedidoDAO();

    public PedidoFacade() {
    }
    
    public void inserir(Pedido order) throws SQLException{
        try{
            pd.insertPedido(order);
        }catch (SQLException ex){
            throw new DbException(ex.getMessage());
        }
    }
        
    public void deletePedido(Pedido pedido){
        try{
            pd.deletePedido(pedido);
        }catch (SQLException ex){
            throw new DbException(ex.getMessage());
        }
    }
  
    public void selectListaPedido(Cliente cliente){
        try{
            pd.selectListaPedido(cliente);
        }catch (SQLException ex){
            throw new DbException(ex.getMessage());
        }    
    }
}
