/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjbdfornecedores_5tabela.menu;
import com.mycompany.prjbdfornecedores_5tabela.objetos.Entrada;
import com.mycompany.prjbdfornecedores_5tabela.objetos.Entrada_Produto;
import com.mycompany.prjbdfornecedores_5tabela.objetos.Fornecedor;
import com.mycompany.prjbdfornecedores_5tabela.objetos.Produto;
import com.mycompany.prjbdfornecedores_5tabela.rn.Entrada_RN;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class CRUD_Entrada
{
    public static void executar(Scanner scanner)
    {
        Entrada_RN entradaRN = new Entrada_RN();
        int opcao;

        do
        {
            System.out.println("=== MENU ENTRADA ===");
            System.out.println("1 - Registrar Entrada");
            System.out.println("2 - Listar Entradas");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao)
            {
                case 1 ->
                {
                    Entrada e = new Entrada();

                    Fornecedor f = new Fornecedor();
                    System.out.print("ID do fornecedor: ");
                    f.setId_fornecedor(scanner.nextInt());
                    scanner.nextLine();
                    e.setFornecedor(f);

                    System.out.print("Data: ");
                    e.setData_entrada(scanner.nextLine());

                    List<Entrada_Produto> itens = new ArrayList<>();
                    double total = 0;

                    System.out.print("Quantos produtos na entrada? ");
                    int qtd = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < qtd; i++)
                    {
                        Entrada_Produto ep = new Entrada_Produto();
                        Produto p = new Produto();

                        System.out.print("ID do produto: ");
                        p.setId_produto(scanner.nextInt());
                        scanner.nextLine();
                        ep.setProduto(p);

                        System.out.print("Quantidade: ");
                        ep.setQuantidade(scanner.nextInt());
                        scanner.nextLine();

                        ep.setEntrada(e);
                        itens.add(ep);
                    }

                    e.setItens_entrada(itens);
                    entradaRN.salvarEntrada(e);
                }
                case 2 -> entradaRN.mostrarEntradas();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}