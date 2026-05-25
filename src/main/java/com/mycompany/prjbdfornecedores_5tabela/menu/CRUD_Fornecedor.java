/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjbdfornecedores_5tabela.menu;

import com.mycompany.prjbdfornecedores_5tabela.objetos.Contato;
import com.mycompany.prjbdfornecedores_5tabela.objetos.Fornecedor;
import com.mycompany.prjbdfornecedores_5tabela.rn.Fornecedor_RN;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CRUD_Fornecedor
{
    public static void executar(Scanner scanner)
    {
        Fornecedor_RN fornecedorRN = new Fornecedor_RN();
        int opcao;

        do
        {
            System.out.println("=== MENU FORNECEDOR/CONTATO ===");
            System.out.println("1 - Cadastrar Fornecedor");
            System.out.println("2 - Listar Fornecedores");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao)
            {
                case 1 ->
                {
                    Fornecedor f = new Fornecedor();
                    System.out.print("Nome: ");
                    f.setNome(scanner.nextLine());
                    System.out.print("CNPJ: ");
                    f.setCnpj(scanner.nextLine());

                    List<Contato> contatos = new ArrayList<>();
                    System.out.print("Quantos contatos deseja adicionar? ");
                    int qtd = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < qtd; i++)
                    {
                        Contato c = new Contato();
                        System.out.print("Tipo do contato: ");
                        c.setTipo_contato(scanner.nextLine());
                        System.out.print("Contato: ");
                        c.setContato(scanner.nextLine());
                        c.setFornecedor(f); // vincula ao fornecedor
                        contatos.add(c);
                    }

                    f.setContatos(contatos);
                    fornecedorRN.salvarFornecedor(f);
                }
                case 2 -> fornecedorRN.mostrarFornecedores();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}