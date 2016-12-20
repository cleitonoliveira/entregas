package com.example.cleiton.final162.DAO;

/**
 * Created by Cleiton on 16/12/2016.
 */

public class Produto {

    int id = 0;
    String nome;
    String tipo;
    Double preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + " - " + tipo + " - " + preco;
    }

}
