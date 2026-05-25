/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjbdfornecedores_5tabela.rn;

import com.mycompany.prjbdfornecedores_5tabela.classes_bd.Contato_BD;
import com.mycompany.prjbdfornecedores_5tabela.classes_bd.Fornecedor_BD;
import com.mycompany.prjbdfornecedores_5tabela.objetos.Contato;
import com.mycompany.prjbdfornecedores_5tabela.objetos.Fornecedor;
import java.util.List;

/**
 *
 * @author clc
 */
public class Fornecedor_RN
{
    Fornecedor_BD f_BD;
    Contato_BD    c_BD;

    public Fornecedor_RN()
    {
        f_BD = new Fornecedor_BD();
        c_BD = new Contato_BD();
    }

    // ----------------------------------------------------------------
    // SALVAR FORNECEDOR (com ou sem contatos)
    // ----------------------------------------------------------------
    public void salvarFornecedor(Fornecedor f)
    {
        if (f.getContatos() != null && !f.getContatos().isEmpty())
        {
            f_BD.salvar(f);

            for (Contato c : f.getContatos())
            {
                c_BD.salvar(c);
            }
        }
        else
        {
            f_BD.salvar(f);
        }
    }

    // ----------------------------------------------------------------
    // MOSTRAR FORNECEDORES (público — busca do BD e delega ao privado)
    // ----------------------------------------------------------------
    public void mostrarFornecedores()
    {
        List<Fornecedor> lstF = f_BD.getFornecedores_Contatos();
        mostrarFornecedores(lstF);
    }

    // ----------------------------------------------------------------
    // MOSTRAR FORNECEDORES (privado — imprime a lista recebida)
    // ----------------------------------------------------------------
    private void mostrarFornecedores(List<Fornecedor> fornecedores)
    {
        for (Fornecedor fornecedor : fornecedores)
        {
            System.out.println("ID: "   + fornecedor.getId_fornecedor());
            System.out.println("Nome: " + fornecedor.getNome());
            System.out.println("CNPJ: " + fornecedor.getCnpj());

            if (fornecedor.getContatos() != null)
            {
                System.out.println("\n");
                mostrarContatos(fornecedor.getContatos());
            }

            System.out.println("\n");
            System.out.println("**********************************************");
            System.out.println("\n");
        }
    }

    // ----------------------------------------------------------------
    // MOSTRAR CONTATOS (privado)
    // ----------------------------------------------------------------
    private void mostrarContatos(List<Contato> contatos)
    {
        for (Contato contato : contatos)
        {
            System.out.println("ID: "           + contato.getId_contato());
            System.out.println("Tipo: "         + contato.getTipo_contato());
            System.out.println("Contato: "      + contato.getContato());
            System.out.println("ID Fornecedor: "+ contato.getFornecedor().getId_fornecedor());
            System.out.println("\n");
        }
    }
}