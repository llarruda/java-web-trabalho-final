/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public class Pedido implements Serializable{
    private int id;
    private LocalDateTime date;
    private Cliente customer;
    private List<ItemDePedido> orderItens;

    public Pedido(int id, LocalDateTime data, Cliente cliente, List<ItemDePedido> itensPedido) {
        this.id = id;
        this.date = data;
        this.customer = cliente;
        this.orderItens = itensPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Cliente getCustomer() {
        return customer;
    }

    public void setCustomer(Cliente customer) {
        this.customer = customer;
    }

    public List<ItemDePedido> getOrderItens() {
        return orderItens;
    }
        
    
}
