/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author llarruda
 */
public class Pedido {
    
    private int id;
    private LocalDateTime data;
    private Cliente cliente;
    private List<ItemDoPedido> itensPedido;
    
    public Pedido(int id, LocalDateTime data, Cliente cliente, List<ItemDoPedido> itensPedido) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.itensPedido = itensPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemDoPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemDoPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }
    
}
