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

    public String exibirPerguntaFormulario(int index, Boolean apenasExibir) {
        System.out.println(perguntasFormulario.get(index));
        if (apenasExibir) {
            return null;
        }
        return sc.nextLine();
    }

    public String exibirPergunta(String pergunta) {
        System.out.println(pergunta);
        return sc.nextLine();
    }

    public void exibirPets() throws FileNotFoundException {
        List<Pet> petCadastrados = petRepository.carregarPetsFile();
        int count = 1;
        for (Pet pet : petCadastrados) {
            System.out.println(count + ". " + pet);
            count++;
        }
    }

    public void exibirPets(List<Pet> listaPets) {
        if (listaPets.isEmpty()) {
            System.out.println("Nenhum pet foi econtrado.");
        }
        int count = 1;
        for (Pet pet : listaPets) {
            System.out.println(count + ". " + pet);
            count++;
        }
    }

    public int exibirMenuCriterioBusca(int numCriterio) {
        System.out.println("## BUSCA COM CRITÉRIO ##");
        System.out.println("[1] Nome ou sobrenome");
        System.out.println("[2] Sexo");
        System.out.println("[3] Idade");
        System.out.println("[4] Peso");
        System.out.println("[5] Raça");
        System.out.println("[6] Endereço");
        System.out.println("[7] Buscar sem novos critérios");
        System.out.printf("Digite o %d° critério: \n", numCriterio);

        try {
            int op = sc.nextInt();
            sc.nextLine();
            return op;
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1;
        }
    }

    public Boolean isCombinarCriteriosBusca() {
        System.out.println("Deseja combinar critérios para uma busca mais específica? ");
        System.out.println("[1] Sim");
        System.out.println("[2] Não");
        try {
            int op = sc.nextInt();
            if (op == 1) return true;
            if (op == 2) return false;
            else throw new IllegalArgumentException();

        } catch (InputMismatchException | IllegalArgumentException e) {
            sc.nextLine();
            return null;
        }
    }
}
