/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.PedidoFacade;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author llarruda
 */
@WebServlet(name = "PedidoServlet", urlPatterns = {"/pedidos/*"})
public class PedidoServlet extends HttpServlet {

    PedidoFacade produtoFacade = new PedidoFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getPathInfo();
        System.out.println(">>> " + acao);
        try {
            switch (acao) {
                /*case "/edit":
                    editarProduto(request, response);
                    break;
                case "/new":
                    novoProduto(request, response);
                    break;*/
                case "/list":
                    listarPedido(request, response);
                    break;
                default:
                    listarPedido(request, response);
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
                /*case "/update":
                    atualizarProduto(request, response);
                    break;
                case "/create":
                    inserirProduto(request, response);
                    break;
                case "/delete":
                    deletarProduto(request, response);
                    break;*/
                default:
                    listarPedido(request, response);
                    //response.sendRedirect(request.getContextPath() + "/home");
                    break;
            }
            // TRATAR EXCEÇÃO    
        } catch (ServletException ex) {
            throw new ServletException(ex);
        }
    }

    protected void listarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /*String nome = request.getParameter("cliente_id");
        

        List lista = produtoFacade.listarPedidos(cliente);

        request.setAttribute("lista", lista);
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/pedidos.jsp");
        rd.forward(request, response);*/
        
        response.sendRedirect(request.getContextPath() + "/pedidos.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
