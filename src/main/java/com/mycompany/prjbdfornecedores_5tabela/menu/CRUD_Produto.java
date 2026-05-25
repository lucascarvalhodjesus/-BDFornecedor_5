/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjbdfornecedores_5tabela.menu;

import com.mycompany.prjbdfornecedores_5tabela.objetos.Produto;
import com.mycompany.prjbdfornecedores_5tabela.rn.Produto_RN;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class CRUD_Produto
{
    public static void executar(Scanner scanner)
    {
        Produto_RN produtoRN = new Produto_RN();
        int opcao;

        do
        {
            System.out.println("=== MENU PRODUTO ===");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao)
            {
                case 1 ->
                {
                    Produto p = new Produto();
                    System.out.print("Nome: ");
                    p.setNome(scanner.nextLine());
                    System.out.print("Valor: ");
                    p.setValor(scanner.nextDouble());
                    scanner.nextLine();
                    produtoRN.salvarProduto(p);
                }
                case 2 -> produtoRN.mostrarProdutos();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}