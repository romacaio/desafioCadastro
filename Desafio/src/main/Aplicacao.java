package main;

import controller.PetController;
import repository.FormularioRepository;
import repository.PetRepository;
import service.PetService;
import service.PetValidator;
import view.ConsoleView;

import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FormularioRepository formularioRepository = new FormularioRepository();
        PetService petService = new PetService();
        PetRepository petRepository = new PetRepository(petService);
        ConsoleView consoleView = new ConsoleView(sc, formularioRepository, petRepository);
        PetValidator petValidator = new PetValidator();
        PetController petController = new PetController(consoleView, petValidator, petRepository);

        formularioRepository.criarFormulario();
        petController.processarMenu();
    }
}
