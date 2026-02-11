package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    private Scanner sc;

    public ConsoleView(Scanner sc) {
        this.sc = sc;
    }

    public int exibirMenu() {
        System.out.println("## MENU ##");
        System.out.println("[1] Cadastrar um novo pet");
        System.out.println("[2] Alterar os dados do pet cadastrado");
        System.out.println("[3] Deletar um pet cadastrado");
        System.out.println("[4] Listar todos os pets cadastrados");
        System.out.println("[5] Listar pets por algum critério (idade, nome, raça)");
        System.out.println("[6] Sair");
        System.out.print("Digite uma opção: ");

        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1;
        }
    }
}
