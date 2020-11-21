/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Junior
 */
public class ClienteDAO {
    private final String stmtInserir = "INSERT INTO cliente(cpf, nome, sobrenome) VALUES(?,?,?)";
    private final String stmtConsultarPorId = "SELECT id, cpf, nome, sobrenome FROM cliente WHERE id = ?";
    private final String stmtConsultarPorCpf = "SELECT id, cpf, nome, sobrenome FROM cliente WHERE cpf = ?";
    private final String stmtListar = "SELECT id, cpf, nome, sobrenome FROM cliente";
    private final String stmtAtualizar = "UPDATE cliente SET cpf = ?, nome = ?, sobrenome = ? WHERE id = ?";
    private final String stmtExcluir = "DELETE FROM cliente WHERE ID = ?";
    private final String stmtQnt = "SELECT COUNT(*) FROM cliente";
    
    public void incluirCliente(Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(stmtInserir, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getSobreNome());
            
            stmt.executeUpdate();
            
            conn.commit();
                    
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um cliente no banco de dados: " + ex.getMessage());
        } finally {
            try{stmt.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar stmt: " + ex.getMessage());}
            try{conn.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar conexao: " + ex.getMessage());}
        }
    }
    
    public Cliente consultarClientePorId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente clienteLido;
        
        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(stmtConsultarPorId);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                clienteLido = new Cliente(rs.getInt("id"), rs.getString("cpf"), rs.getString("nome"), rs.getString("sobrenome"));
                
                return clienteLido;
            }else{
                throw new RuntimeException("Não existe cliente com este id. ID = " + id);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar um cliente no banco de dados. " + ex.getMessage());
        } finally{
            try{rs.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar result set: " + ex.getMessage());}
            try{stmt.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar stmt: " + ex.getMessage());}
            try{conn.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar conexao: " + ex.getMessage());}
        }
    }
    
    public Cliente consultarClientePorCpf(String cpf) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente clienteLido;
        
        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(stmtConsultarPorCpf);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            if(rs.next()){
                clienteLido = new Cliente(rs.getInt("id"), rs.getString("cpf"), rs.getString("nome"), rs.getString("sobrenome"));
                
                return clienteLido;
            }else{
                throw new RuntimeException("Não existe cliente com este CPF: " + cpf);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar cliente no banco de dados por CPF. " + ex.getMessage());
        } catch(RuntimeException ex) {
            return null;
        }
            finally{
            try{rs.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar result set: " + ex.getMessage());}
            try{stmt.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar stmt: " + ex.getMessage());}
            try{conn.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar conexao: " + ex.getMessage());}
        }
    }
    
    public List<Cliente> listarClientes() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Cliente> lista = new ArrayList();
        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("cpf"), rs.getString("nome"), rs.getString("sobrenome"));
                cliente.setId(rs.getInt("id"));
                lista.add(cliente);
            }
            return lista;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar clientes no banco de dados. " + ex.getMessage());
        } finally{
            try{rs.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar result set: " + ex.getMessage());}
            try{stmt.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar stmt: " + ex.getMessage());}
            try{conn.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar conexao: " + ex.getMessage());}
        }

    }
    
    public int getQuantidadeClientes() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int quantidade;

        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(stmtQnt);
            rs = stmt.executeQuery();
            rs.next();
            quantidade = rs.getInt(1);
            
            return quantidade;
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar quantidade de clientes no banco de dados. " + ex.getMessage());
        } finally{
            try{rs.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar result set: " + ex.getMessage());}
            try{stmt.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar stmt: " + ex.getMessage());}
            try{conn.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar conexao: " + ex.getMessage());}
        }

    }
    
    public void atualizarCliente(Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(stmtAtualizar);
            
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getSobreNome());
            stmt.setInt(4, cliente.getId());
            
            stmt.executeUpdate();
            
            conn.commit();
                    
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atulizar um cliente no banco de dados: " + ex.getMessage());
        } finally {
            try{stmt.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar stmt: " + ex.getMessage());}
            try{conn.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar conexao: " + ex.getMessage());}
        }
    }
    
    public void excluirCliente(Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(stmtExcluir);
            
            stmt.setInt(1, cliente.getId());
            
            stmt.executeUpdate();
            
            conn.commit();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir um cliente no banco de dados: " + ex.getMessage());
        } finally {
            try{stmt.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar stmt: " + ex.getMessage());}
            try{conn.close();}catch(SQLException ex){throw new RuntimeException("Erro ao fechar conexao: " + ex.getMessage());}
        }
    }    
}
