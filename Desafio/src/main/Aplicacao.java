package main;

import controller.PetController;
import repository.FormularioRepository;
import view.ConsoleView;

import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FormularioRepository formulario = new FormularioRepository();
        ConsoleView console = new ConsoleView(sc);
        PetController petController = new PetController(console);

        formulario.criarFormulario();
        petController.processarMenu();
    }
}
