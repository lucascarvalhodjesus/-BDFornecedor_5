/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjbdfornecedores_5tabela.rn;


import com.mycompany.prjbdfornecedores_5tabela.classes_bd.Entrada_BD;
import com.mycompany.prjbdfornecedores_5tabela.classes_bd.Produto_BD;
import com.mycompany.prjbdfornecedores_5tabela.objetos.Entrada;
import java.util.List;

import com.mycompany.prjbdfornecedores_5tabela.objetos.Entrada_Produto;

import com.mycompany.prjbdfornecedores_5tabela.objetos.Entrada_Produto;
import com.mycompany.prjbdfornecedores_5tabela.objetos.Produto;

/**
 *
 * @author user
 */
public class Entrada_RN
{
    Entrada_BD e_BD;
    Produto_BD p_BD;

    public Entrada_RN()
    {
        e_BD = new Entrada_BD();
        p_BD = new Produto_BD();
    }

    // ----------------------------------------------------------------
    // SALVAR ENTRADA (calcula total, salva entrada e seus itens)
    // ----------------------------------------------------------------
    public void salvarEntrada(Entrada e)
    {
        calcularTotalEntrada(e);
        e_BD.salvar(e);

        if (e.getId_entrada() != 0)
        {
            for (Entrada_Produto ep : e.getItens_entrada())
            {
                e_BD.salvarEntradaProduto(ep);
            }
        }
    }

    // ----------------------------------------------------------------
    // CALCULAR TOTAL DA ENTRADA (privado)
    // ----------------------------------------------------------------
    private void calcularTotalEntrada(Entrada e)
    {
        double total = 0;

        for (Entrada_Produto ep : e.getItens_entrada())
        {
            Produto p = ep.getProduto();
            p_BD.getProduto(p);
            total += p.getValor() * ep.getQuantidade();
        }

        e.setTotal_entrada(total);
    }

    // ----------------------------------------------------------------
    // MOSTRAR ENTRADAS (público)
    // ----------------------------------------------------------------
    public void mostrarEntradas()
    {
        List<Entrada> lstE = e_BD.getEntradas();
        mostrarEntradas(lstE);
    }

    // ----------------------------------------------------------------
    // MOSTRAR ENTRADAS (privado)
    // ----------------------------------------------------------------
    private void mostrarEntradas(List<Entrada> entradas)
    {
        for (Entrada entrada : entradas)
        {
            System.out.println("ID: "             + entrada.getId_entrada());
            System.out.println("Data da entrada: "+ entrada.getData_entrada());
            System.out.println("Total da entrada: "+ entrada.getTotal_entrada());
            System.out.println("Fornecedor: "     + entrada.getFornecedor().getNome());

            if (entrada.getItens_entrada() != null)
            {
                System.out.println("\n");
                mostrarItens(entrada.getItens_entrada());
            }

            System.out.println("\n");
            System.out.println("**********************************************");
            System.out.println("\n");
        }
    }

    // ----------------------------------------------------------------
    // MOSTRAR ITENS DA ENTRADA (privado)
    // ----------------------------------------------------------------
    private void mostrarItens(List<Entrada_Produto> lstEP)
    {
        for (Entrada_Produto ep : lstEP)
        {
            System.out.println("ID Entrada: " + ep.getEntrada().getId_entrada());
            System.out.println("ID Produto: " + ep.getProduto().getId_produto());
            System.out.println("Produto"      + ep.getProduto().getNome());
            System.out.println("Valor: "      + ep.getProduto().getValor());
            System.out.println("Quantidade: " + ep.getQuantidade());
            System.out.println("\n");
        }
    }
}