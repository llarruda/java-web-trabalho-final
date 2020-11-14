/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.ProdutoFacade;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;

/**
 *
 * @author Jordi.Santos
 */
@WebServlet(name = "ProdutoServlet", urlPatterns = {"/"})
public class ProdutoServlet extends HttpServlet {
    
    ProdutoFacade pf = new ProdutoFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getServletPath();
        System.out.println(acao);
        try {
            switch (acao) {
                case "/editar":
                    atualizarProduto(request, response);
                    break;
                case "/novo":
                    novoProduto(request, response);
                    break;
                default:
                    findAll(request, response);
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
                    update(request, response);
                    break;
                case "/criar":
                    insert(request, response);
                    break;
                case "/excluir":
                    deleteById(request, response);
                    break;
                default:
                    findAll(request, response);
                    break;
            }
            // TRATAR EXCEÇÃO    
        } catch (ServletException ex) {
            throw new ServletException(ex);
        }

    }

    protected void findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List lista = pf.findAll();
        for(int i=0; i < lista.size(); i++){
            System.out.println(lista.get(i));
        }
        request.setAttribute("lista", lista);
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/produtos.jsp");
        rd.forward(request, response);
    }

    protected void insert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String descricao = request.getParameter("descricao");
        if(descricao.isEmpty()) {
            request.setAttribute("msgerro", "Nome não pode ser vazio.");
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
            return;
        }
            Produto p = new Produto(0, descricao);
            //System.out.println(c.getNome() + c.getSobreNome() + c.getCpf());
            pf.insert(p);

            boolean sucesso = true;
            request.getSession().setAttribute("sucessomsg", sucesso);

            response.sendRedirect("produtos");
    } // fim do insert

    protected void novoProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("novoproduto.jsp");
        rd.forward(request, response);
    }

    protected void atualizarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Produto p = pf.findById(id);

        request.setAttribute("produto", p);
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/editarproduto.jsp");
        rd.forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String descricao = request.getParameter("descricao");

            Produto p = new Produto(id, descricao);
            pf.update(p);

            boolean alterar = true;
            request.getSession().setAttribute("alterarmsg", alterar);

            response.sendRedirect("produtos");
    }

    protected void deleteById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        pf.deleteById(id);

        boolean excluir = true;
        request.getSession().setAttribute("excluirmsg", excluir);

        response.sendRedirect("produtos");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
