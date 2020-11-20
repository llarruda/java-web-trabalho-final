/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facade.ProdutoFacade;
import model.Produto;

/**
 *
 * @author llarruda
 */
@WebServlet(name = "ProdutoServlet", urlPatterns = {"/produtos/*"})
public class ProdutoServlet extends HttpServlet {

    
    ProdutoFacade produtoFacade = new ProdutoFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getPathInfo();
        System.out.println(">>> " + acao);
        try {
            switch (acao) {
                case "/edit":
                    editarProduto(request, response);
                    break;
                case "/new":
                    novoProduto(request, response);
                    break;
                case "/list":
                    listarProdutos(request, response);
                    break;
                default:
                    listarProdutos(request, response);
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
                case "/update":
                    atualizarProduto(request, response);
                    break;
                case "/create":
                    inserirProduto(request, response);
                    break;
                case "/delete":
                    deletarProduto(request, response);
                    break;
                default:
                    listarProdutos(request, response);
                    //response.sendRedirect(request.getContextPath() + "/home");
                    break;
            }
            // TRATAR EXCEÇÃO    
        } catch (ServletException ex) {
            throw new ServletException(ex);
        }
    }

    protected void listarProdutos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List lista = produtoFacade.listarProdutos();

        request.setAttribute("lista", lista);
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/produtos.jsp");
        rd.forward(request, response);
    }

    protected void inserirProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String descricao = request.getParameter("descricao");
        
        if(descricao.isEmpty()) {
            request.setAttribute("msgerro", "Descricão do Produto não informada.");
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
            return;
        }   

        List<Produto> listaProduto = produtoFacade.listarProdutos();
        boolean descricaoExists = false;
        for (int i = 0; i < listaProduto.size(); i++) {
            if (descricao.equals(listaProduto.get(i).getDescricao())) {
                descricaoExists = true;
                break;
            }
        }

        if (descricaoExists) {
            request.getSession().setAttribute("descricaoExists", descricaoExists);
            response.sendRedirect("new");
        } else {
            Produto produto = new Produto(1, descricao);
            produtoFacade.inserir(produto);

            boolean sucesso = true;
            request.getSession().setAttribute("sucessomsg", sucesso);

            response.sendRedirect("list");
        }
    }

    protected void novoProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/novoProduto.jsp");
        rd.forward(request, response);
    }

    protected void editarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Produto produto = produtoFacade.selectProdById(id);

        request.setAttribute("produto", produto);
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/editarProduto.jsp");
        rd.forward(request, response);
    }

    protected void atualizarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String descricao = request.getParameter("descricao");
        
        // TODO: consultar cliente por cpf, se houver mais de 1 registro ou se o registro for único, mas o id do cliente for diferente do registro editado no momento, não permitir editar
        List<Produto> listaProduto = produtoFacade.listarProdutos();
        boolean descricaoExists = false;
        for (int i = 0; i < listaProduto.size(); i++) {
            if (descricao.equals(listaProduto.get(i).getDescricao())) {
                descricaoExists = true;
                break;
            }
        }
        
        if (descricaoExists) {
            request.getSession().setAttribute("descricaoExists", descricaoExists);
            String previousURL = request.getHeader("referer");
            response.sendRedirect(previousURL);
        } else {
            Produto produto = new Produto(id, descricao);
            produtoFacade.atualizar(produto);

            boolean alterar = true;
            request.getSession().setAttribute("alterarmsg", alterar);

            response.sendRedirect("clientes");
        }
    }

    protected void deletarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        produtoFacade.deletar(id);

        boolean excluir = true;
        request.getSession().setAttribute("excluirmsg", excluir);

        response.sendRedirect("produtos");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}