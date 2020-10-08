/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Jordi.Santos
 */
public class Order implements Serializable{
    private int id;
    private LocalDateTime date;
    private Customer customer;
    private List<OrderItem> orderItens;

    public Order(int id, LocalDateTime data, Customer cliente, List<OrderItem> itensPedido) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItens() {
        return orderItens;
    }
        
    
}
