package main;

import controller.PetController;
import repository.FormularioRepository;
import service.PetValidator;
import view.ConsoleView;

import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FormularioRepository formulario = new FormularioRepository();
        ConsoleView console = new ConsoleView(sc, formulario);
        PetValidator petValidator = new PetValidator();
        PetController petController = new PetController(console, petValidator);

        formulario.criarFormulario();
        petController.processarMenu();
    }
}
