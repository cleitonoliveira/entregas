package com.example.cleiton.final162.DAO;

/**
 * Created by Cleiton on 16/12/2016.
 */

public class PE {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return produto +" - "+ qtde +" - "+ preco;
    }

    int id = 0;
    int cliente;
    int produto;
    int qtde;
    double preco;
}
