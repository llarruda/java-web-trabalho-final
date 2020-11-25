/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.ClienteFacade;
import facade.ItemDoPedidoFacade;
import facade.PedidoFacade;
import facade.ProdutoFacade;
import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.ItemDoPedido;
import model.Pedido;
import model.Produto;

/**
 *
 * @author llarruda
 */
@WebServlet(name = "PedidoServlet", urlPatterns = {"/pedidos/*"})
public class PedidoServlet extends HttpServlet {

    PedidoFacade pedidoFacade = new PedidoFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getPathInfo();
        System.out.println(">>> " + acao);
        try {
            switch (acao) {
                case "/detail":
                    detalharPedido(request, response);
                    break;
                case "/new":
                    novoPedido(request, response);
                    break;
                case "/list":
                    listarPedidos(request, response);
                    break;
                default:
                    listarPedidos(request, response);
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
                    break;*/
                case "/create":
                    registerPedido(request, response);
                    break;
                case "/delete":
                    deletarPedido(request, response);
                    break;
                case "/teste":
                    searchProdutoByDesc(request, response);
                    break;
                case "/addItens":
                    addItensPedido(request, response);
                    break;
                case "/search":
                    searchPedidoByCliente(request, response);
                    break;
                default:
                    listarPedidos(request, response);
                    //response.sendRedirect(request.getContextPath() + "/home");
                    break;
            }
            // TRATAR EXCEÇÃO    
        } catch (ServletException ex) {
            throw new ServletException(ex);
        }
    }

    protected void listarPedidos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Pedido> lista = pedidoFacade.listarPedidos();
        for (Pedido p : lista) {
                System.out.printf("LISTA DE PEDIDOS \n%s %d\n", p.getCliente().getCpf(), p.getCliente().getId());
        }
        request.setAttribute("lista", lista);
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/pedidos.jsp");
        rd.forward(request, response);
        
        //response.sendRedirect(request.getContextPath() + "/pedidos.jsp");
    }
    
    public void novoPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd = request.getRequestDispatcher("/novoPedido.jsp");
        rd.forward(request, response);
    }
    
    public void registerPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PedidoFacade pedidoFacade = new PedidoFacade();
        ClienteFacade clienteFacade = new ClienteFacade();
        ProdutoFacade produtoFacade = new ProdutoFacade();
        
        LocalDateTime dataPedido = LocalDateTime.now(Clock.systemUTC());
        
        String cpf = request.getParameter("cpf");
        
        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replaceAll("-", "");
        
        Cliente cliente = clienteFacade.buscarPorCpf(cpf);
        
        if (cliente == null) {
            boolean cpfNotFound = true;
            request.getSession().setAttribute("cpfNotFound", cpfNotFound);
            request.getSession().setAttribute("cpf_consultado", cpf);
            String previousURL = request.getHeader("referer");
            response.sendRedirect(previousURL);
        } else {
            
            Pedido pedido = new Pedido(1, dataPedido, cliente, null);

            pedidoFacade.inserir(pedido);
            
            request.getSession().setAttribute("pedido_id", pedido.getId());
            
            boolean pedidoInserido = true;
            request.getSession().setAttribute("sucessomsg", pedidoInserido);
            
            
            List listaProdutos = produtoFacade.listarProdutos();

            request.setAttribute("listaProdutos", listaProdutos);
            
            //response.sendRedirect("pedidos");
            
            //RequestDispatcher rd = request.getRequestDispatcher("/addItensPedido.jsp");
            //rd.forward(request, response);
            
            RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/addItensPedido.jsp");
            rd.forward(request, response);
        }
    }
    
    protected void searchProdutoByDesc(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String descricao = request.getParameter("descricao");
        
        ProdutoFacade produtoFacade = new ProdutoFacade();

        //Produto produto = produtoFacade.searchProdutoByDesc(descricao);
        
        List<Produto> lista = produtoFacade.searchProdutoByDesc(descricao);
        
        if (lista.isEmpty()) {
            System.out.println(">>>>> TESTE");
            boolean produtoNotFound = true;
            request.getSession().setAttribute("produtoNotFound", produtoNotFound);
            request.getSession().setAttribute("descricao", descricao);
            lista = produtoFacade.listarProdutos();
        }
        

        request.setAttribute("lista", lista);

        RequestDispatcher rd = getServletContext()
            .getRequestDispatcher("/produtos.jsp");
        rd.forward(request, response);
    }
    
     protected void searchPedidoByCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String cpf = request.getParameter("cpf");
        request.getSession().setAttribute("cpf_consultado", cpf);

        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replaceAll("-", "");
        
        PedidoFacade pedidoFacade = new PedidoFacade();
        ClienteFacade clienteFacade = new ClienteFacade();

        Cliente cliente = clienteFacade.buscarPorCpf(cpf);
        
        List<Pedido> lista;
        
        
        if (cliente == null) {
            boolean cpfNotFound = true;
            request.getSession().setAttribute("cpfNotFound", cpfNotFound);
            
            lista = pedidoFacade.listarPedidos();
               
        } else {
            lista = pedidoFacade.listarPedidosCliente(cliente);
            
            if (lista.size() < 1) {
                request.getSession().setAttribute("clienteSemPedido", true);
                lista = pedidoFacade.listarPedidos();
            }
        }
        
        request.setAttribute("lista", lista);
             RequestDispatcher rd = getServletContext()
                     .getRequestDispatcher("/pedidos.jsp");
             rd.forward(request, response);
    }
    
    public void addItensPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id[] = request.getParameterValues("id");
        String quantidade[] = request.getParameterValues("quantidade");
        
        int pedidoGerado = Integer.parseInt(request.getParameter("pedido_gerado"));
        Pedido pedido = new Pedido(pedidoGerado, null, null, null);
        System.out.println(pedidoGerado);
        
        if (id == null) {
           pedidoFacade.deletar(pedido);
           request.getSession().setAttribute("pedidoSemItens", true);
        } else {
            int totalItens = 0;
                    
            for(int i = 0; i < id.length; i++) {
                int qtdParsed = Integer.parseInt(quantidade[i]);
                totalItens += qtdParsed;

                if (qtdParsed > 0) { 
                    Produto prod = new Produto(Integer.parseInt(id[i].trim()), "");
                    ItemDoPedido item = new ItemDoPedido(prod, qtdParsed);

                    ItemDoPedidoFacade itemPedidoFacade = new ItemDoPedidoFacade();

                    itemPedidoFacade.inserir(item, pedido);
                }
            }
            
            // DEBUG
            System.out.println("Quantidade Itens > " + totalItens);
            
            if (totalItens == 0) {
                pedidoFacade.deletar(pedido);
                request.getSession().setAttribute("pedidoSemItens", true);
            }
        }
        
        // DEBUG
        /*Enumeration<String> parameterNames = request.getParameterNames();
 
        while (parameterNames.hasMoreElements()) {
 
            String paramName = parameterNames.nextElement();
            System.out.println(">>>>>" + paramName);
 
        } 
        
        for (Pedido p : lista) {
                System.out.printf("LISTA DE PEDIDOS \n%s %d\n", p.getCliente().getCpf(), p.getCliente().getId());
        }*/
        List<Pedido> lista = pedidoFacade.listarPedidos();
        request.setAttribute("lista", lista);
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/pedidos.jsp");
        rd.forward(request, response);
    }
    
    protected void detalharPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Pedido pedido = new Pedido(id, null, null, null);
        
        
        PedidoFacade pedidoFacade = new PedidoFacade();
         
        List<Pedido> lista = pedidoFacade.listarPedidosPorId(pedido);
        request.setAttribute("lista", lista);
        
        List<ItemDoPedido> itensPedido = pedidoFacade.listarItensPedido(pedido);  
        request.setAttribute("listaItens", itensPedido);
     
             RequestDispatcher rd = getServletContext()
                     .getRequestDispatcher("/detalhesPedido.jsp");
             rd.forward(request, response);
    }
    
    protected void deletarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        
        Pedido pedido = new Pedido(id, null, null, null);
        pedidoFacade.deletar(pedido);

        boolean excluir = true;
        request.getSession().setAttribute("excluirmsg", excluir);

        response.sendRedirect("pedidos");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
