package controller;

import view.ConsoleView;

public class PetController {
    private ConsoleView consoleView;

    public PetController(ConsoleView consoleView) {
        this.consoleView = consoleView;
    }

    public void processarMenu() {
        int op = consoleView.exibirMenu();
        while (op != 6) {

            switch (op) {
                //case 1 ->
                //case 2 ->
                //case 3 ->
                //case 4 ->
                //case 5 ->
                case -1 -> System.out.println("Opção inválida! Apenas números são permitidos.\n");
                default -> System.out.println("Opção inválida! Digite um número correspondente a uma opção válida.\n");
            }
            op = consoleView.exibirMenu();
        }
        System.out.println("Programa encerrado...");
        System.exit(0);
    }

}
