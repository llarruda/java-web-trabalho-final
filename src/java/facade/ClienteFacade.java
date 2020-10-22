/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dao.ClienteDAO;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Junior
 */
public class ClienteFacade {
    private ClienteDAO clienteDAO = new ClienteDAO();
    
    public ClienteFacade() {
        
    }
    
    public void inserir(Cliente cliente){
        clienteDAO.incluirCliente(cliente);
    }
    
    public List listar() {
        return clienteDAO.listarClientes();
    }
    
    public int quantidade() {
        int qnt = clienteDAO.getQuantidadeClientes();
        return qnt;
    }
    
}
