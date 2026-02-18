package view;

import model.Pet;
import repository.FormularioRepository;
import repository.PetRepository;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private Scanner sc;
    private List<String> perguntasFormulario;
    private PetRepository petRepository;

    public ConsoleView(Scanner sc, FormularioRepository formulario, PetRepository petRepository) {
        this.sc = sc;
        this.perguntasFormulario = formulario.lerFormulario();
        this.petRepository = petRepository;
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
            int op = sc.nextInt();
            sc.nextLine();
            return op;
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1;
        }
    }

    public String exibirPergunta(int index, Boolean apenasExibir) {
        System.out.println(perguntasFormulario.get(index));
        if (apenasExibir) {
            return null;
        }
        return sc.nextLine();
    }

    public String exibirPerguntaEndereco(String perguntaEndereco) {
        System.out.print(perguntaEndereco);
        return sc.nextLine();
    }

    public void listarPetsCadastrados() throws FileNotFoundException {
        List<Pet> petCadastrados = petRepository.carregarPetsFile();
        int count = 1;
        for (Pet pet : petCadastrados) {
            System.out.println(count + ". " + pet);
            count++;
        }
    }
}
