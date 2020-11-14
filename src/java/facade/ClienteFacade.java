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
    
    public void inserir(Cliente cliente) {
        clienteDAO.incluirCliente(cliente);
    }
    
    public List listar() {
        return clienteDAO.listarClientes();
    }
    
    public Cliente buscar(int id) {
        return clienteDAO.consultarCliente(id);
    }
    
    public void atualizar(Cliente cliente) {
        clienteDAO.atualizarCliente(cliente);
    }
    
    public int quantidade() {
        int qnt = clienteDAO.getQuantidadeClientes();
        return qnt;
    }
    
    public void deletar(int id) {
        Cliente cliente = clienteDAO.consultarCliente(id);
        clienteDAO.excluirCliente(cliente);
    }
    
}
