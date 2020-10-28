/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.ClienteFacade;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;

/**
 *
 * @author Junior
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/"})
public class ClienteServlet extends HttpServlet {

    ClienteFacade cf = new ClienteFacade();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getServletPath();
        System.out.println(acao);
        try {
            switch (acao) {
                case "/editar":
                    editarCliente(request, response);
                    break;
                case "/novo":
                    novoCliente(request, response);
                    break;
                default:
                    listarCliente(request, response);
                    break;
            }
        // TRATAR EXCEÇÃO    
        } catch (ServletException ex) {
            throw new ServletException(ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getServletPath();
        System.out.println(acao);
        try {
            switch (acao) {
                case "/atualizar":
                    atualizarCliente(request, response);
                    break;
                case "/criar":
                    inserirCliente(request, response);
                    break;
                case "/excluir":
                    deletarCliente(request, response);
                    break;
                default:
                    listarCliente(request, response);
                    break;
            }
        // TRATAR EXCEÇÃO    
        } catch (ServletException ex) {
            throw new ServletException(ex);
        }
        
    }

    protected void listarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            List lista = cf.listar();

            request.setAttribute("lista", lista);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/clientes.jsp");
            rd.forward(request, response);   
    }
    
    protected void inserirCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String nome = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            String cpf = request.getParameter("cpf");
            
            Cliente c = new Cliente(0, cpf, nome, sobrenome);
            //System.out.println(c.getNome() + c.getSobreNome() + c.getCpf());
            cf.inserir(c);
            
            boolean sucesso = true;
            request.getSession().setAttribute("sucessomsg", sucesso);
            
            response.sendRedirect("clientes");
    }
    
    protected void novoCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd = request.getRequestDispatcher("novocliente.jsp");
        rd.forward(request, response);
    }
    
    protected void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            int id = Integer.parseInt(request.getParameter("id"));
        
            Cliente c = cf.buscar(id);

            request.setAttribute("cliente", c);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/editarcliente.jsp");
            rd.forward(request, response);    
    }
    
        protected void atualizarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            String cpf = request.getParameter("cpf");
            
            Cliente c = new Cliente(id, cpf, nome, sobrenome);
            //System.out.println(c.getNome() + c.getSobreNome() + c.getCpf());
            cf.atualizar(c);
            
            boolean alterar = true;
            request.getSession().setAttribute("alterarmsg", alterar);      
            
            response.sendRedirect("clientes");
    }
        
    protected void deletarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        cf.deletar(id);
        
        boolean excluir = true;
        request.getSession().setAttribute("excluirmsg", excluir);
        
        response.sendRedirect("clientes");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
