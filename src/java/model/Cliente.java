/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author Junior
 */
public class Cliente {
    private int id;
    private String cpf;
    private String nome;
    private String sobreNome;

    public Cliente(int id, String cpf, String nome, String sobreNome) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.sobreNome = sobreNome;
    } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", sobreNome=" + sobreNome + '}';
    }
}
