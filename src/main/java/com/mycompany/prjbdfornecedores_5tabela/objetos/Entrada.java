/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjbdfornecedores_5tabela.objetos;

import java.util.List;

/**
 *
 * @author carlos
 */
public class Entrada {
    private int id_entrada;
    private String data_entrada;
    private double total_entrada;
    private Fornecedor fornecedor;
    private List<Entrada_Produto> itens_entrada;

    public int getId_entrada() {
        return id_entrada;
    }

    public void setId_entrada(int id_entrada) {
        this.id_entrada = id_entrada;
    }

    public String getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(String data_entrada) {
        this.data_entrada = data_entrada;
    }

    public double getTotal_entrada() {
        return total_entrada;
    }

    public void setTotal_entrada(double total_entrada) {
        this.total_entrada = total_entrada;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Entrada_Produto> getItens_entrada() {
        return itens_entrada;
    }

    public void setItens_entrada(List<Entrada_Produto> itens_entrada) {
        this.itens_entrada = itens_entrada;
    }
    
    
}
