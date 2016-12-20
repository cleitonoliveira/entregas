package com.example.cleiton.final162.DAO;

/**
 * Created by Cleiton on 16/12/2016.
 */

public class Entrega {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    int id = 0;
    String endereco;
    String cliente;
    String obs;
    boolean entregue;

    @Override
    public String toString() {
        return cliente + " - " + endereco;
    }
}
