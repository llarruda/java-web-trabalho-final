/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.ClienteFacade;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "ClienteServlet", urlPatterns = {"/clientes/*"})
public class ClienteServlet extends HttpServlet {

    ClienteFacade cf = new ClienteFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getPathInfo();
        System.out.println(">>> " + acao);
        try {
            switch (acao) {
                case "/editar":
                    editarCliente(request, response);
                    break;
                case "/novo":
                    novoCliente(request, response);
                    break;
                case "/listar":
                    listarCliente(request, response);
                    break;
                default:
                    listarCliente(request, response);
                    //response.sendRedirect(request.getContextPath() + "/home");
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

        String acao = request.getPathInfo();
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
                case "/buscar":
                    buscarClientePorCpf(request, response);
                    break;
                default:
                    // listarCliente(request, response);
                    response.sendRedirect(request.getContextPath() + "/home");
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
        if(nome.isEmpty()) {
            request.setAttribute("msgerro", "Nome não pode ser vazio.");
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
            return;
        }

        String sobrenome = request.getParameter("sobrenome");
        if (sobrenome.isEmpty()) {
            request.setAttribute("msgerro", "Sobrenome não pode ser vazio.");
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
            return;
        }
        
        String cpf = request.getParameter("cpf");
        if(cpf.isEmpty()) {
            request.setAttribute("msgerro", "CPF não pode ser vazio.");
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
            return;
        }
        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replaceAll("-", "");        

        List<Cliente> listaCliente = cf.listar();
        boolean existecpf = false;
        for (int i = 0; i < listaCliente.size(); i++) {
            if (cpf.equals(listaCliente.get(i).getCpf())) {
                existecpf = true;
                break;
            }
        }

        if (existecpf) {
            request.getSession().setAttribute("existecpf", existecpf);
            response.sendRedirect("novo");
        } else {
            Cliente c = new Cliente(0, cpf, nome, sobrenome);
            //System.out.println(c.getNome() + c.getSobreNome() + c.getCpf());
            cf.inserir(c);

            boolean sucesso = true;
            request.getSession().setAttribute("sucessomsg", sucesso);

            response.sendRedirect("list");
        }

    }

    protected void novoCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/novocliente.jsp");
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
        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replaceAll("-", "");
        
        // TODO: consultar cliente por cpf, se houver mais de 1 registro ou se o registro for único, mas o id do cliente for diferente do registro editado no momento, não permitir editar
        List<Cliente> listaCliente = cf.listar();
        boolean existecpf = false;
        for (int i = 0; i < listaCliente.size(); i++) {
            if (cpf.equals(listaCliente.get(i).getCpf())) {
                existecpf = true;
                break;
            }
        }
        
        Cliente cliente = cf.buscar(id);
        
        if ((existecpf) && !cliente.getCpf().equals(cpf)) {
            request.getSession().setAttribute("existecpf", existecpf);
            String previousURL = request.getHeader("referer");
            response.sendRedirect(previousURL);
        } else {
            Cliente c = new Cliente(id, cpf, nome, sobrenome);
            cf.atualizar(c);

            boolean alterar = true;
            request.getSession().setAttribute("alterarmsg", alterar);

            response.sendRedirect("clientes");
        }
    }

    protected void deletarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        cf.deletar(id);

        boolean excluir = true;
        request.getSession().setAttribute("excluirmsg", excluir);

        response.sendRedirect("clientes");
    }
    
    protected void buscarClientePorCpf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String cpf = request.getParameter("cpf");
        
        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replaceAll("-", "");
        
        ClienteFacade cf = new ClienteFacade();

        Cliente cliente = cf.buscarPorCpf(cpf);
        
        List<Cliente> lista = new ArrayList<Cliente>();
        
        if (cliente == null) {
            boolean cpfNotFound = true;
            request.getSession().setAttribute("cpfNotFound", cpfNotFound);
            request.getSession().setAttribute("cpf_consultado", cpf);
            
            lista = cf.listar();    
        } else {
            lista.add(cliente);
        }

        request.setAttribute("lista", lista);

        RequestDispatcher rd = getServletContext()
            .getRequestDispatcher("/clientes.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
