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
        return "Produto: "+nomeProduto+" - Qtde: "+ qtde +" - Preço: R$"+ preco;
    }


    public int getEntrega() {
        return entrega;
    }

    public void setEntrega(int entrega) {
        this.entrega = entrega;
    }

    int id = 0;
    String nomeProduto;

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    int entrega;
    int produto;
    int qtde;
    double preco;
}
