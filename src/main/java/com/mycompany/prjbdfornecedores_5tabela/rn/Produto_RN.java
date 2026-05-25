/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjbdfornecedores_5tabela.rn;

import com.mycompany.prjbdfornecedores_5tabela.classes_bd.Produto_BD;
import com.mycompany.prjbdfornecedores_5tabela.objetos.Produto;
import java.util.List;

/**
 *
 * @author user
 */
public class Produto_RN
{
    Produto_BD p_BD;

    public Produto_RN()
    {
        p_BD = new Produto_BD();
    }

    public void salvarProduto(Produto p)
    {
        p_BD.salvar(p);
    }

    public void mostrarProdutos()
    {
        List<Produto> produtos = p_BD.getProdutos();
        for (Produto produto : produtos)
        {
            System.out.println("ID: "    + produto.getId_produto());
            System.out.println("Nome: "  + produto.getNome());
            System.out.println("Valor: " + produto.getValor());
            System.out.println("\n");
        }
    }
}