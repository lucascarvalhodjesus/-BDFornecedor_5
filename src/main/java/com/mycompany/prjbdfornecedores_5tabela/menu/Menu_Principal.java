/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjbdfornecedores_5tabela.menu;

import java.util.Scanner;

public class Menu_Principal {
    public static void menuPrincipal()
    {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
 
        while (opcao != 0)
        {
            System.out.println("====== MENU PRINCIPAL======");
            System.out.println("1 - Fornecedor e Contato");
            System.out.println("2 - Produto");
            System.out.println("3 - Entrada");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
 
            switch (opcao)
            {
                case 1 -> CRUD_Fornecedor.executar(scanner);
                case 2 -> CRUD_Produto.executar(scanner);
                case 3 -> CRUD_Entrada.executar(scanner);
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida.");
            }
        }
 
        scanner.close();
    }
}
