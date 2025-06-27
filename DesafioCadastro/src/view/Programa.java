package view;

import model.entidades.Pet;
import model.entidades.Tipo;
import model.io.RespostasFile;
import model.services.BuscarPet;
import model.services.CadastrarPet;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Boolean continua = true;
        while (continua) {
            System.out.println("[1] - Cadastrar um novo pet");
            System.out.println("[2] - Alterar os dados do pet cadastrado");
            System.out.println("[3] - Deletar um pet cadastrado");
            System.out.println("[4] - Listar todos os pets cadastrados");
            System.out.println("[5] - Listar pets por algum critério (idade, nome, raça)");
            System.out.println("[6] - Sair");

            int op = 0;

            try {
                op = sc.nextInt();
                sc.nextLine();
                if (op <= 0 || op > 6) {
                    throw new IllegalArgumentException("Digite uma ação Válida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("\nErro: Digite apenas números.");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("\nErro: " + e.getMessage());
            }
            switch (op) {
                case 1:
                    Pet petCadastrado = CadastrarPet.cadastrar();
                    RespostasFile.criarRespostas(petCadastrado);
                    break;
                case 5:
                    String tipo;
                    System.out.println("BUSCAR PET CADASTRADO");
                    System.out.println("----------------------------------");
                    while (true) {
                        System.out.print("Primeiro, informe o TIPO de animal: ");
                        try {
                            Tipo tipoPet = Tipo.tipoPorNomeRelatorio(sc.nextLine());
                            if (tipoPet == null) {
                                throw new IllegalArgumentException("Tipo Inexistente.");
                            }
                            tipo = tipoPet.getNomeRelatorio();
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                    }
                    while (true) {
                        System.out.println("Agora selecione os critérios de busca.");
                        System.out.println("Você pode escolher até 2 critérios combinados:");
                        System.out.println("[1] - Nome ou Sobrenome");
                        System.out.println("[2] - Sexo");
                        System.out.println("[3] - Idade");
                        System.out.println("[4] - Peso");
                        System.out.println("[5] - Raça");
                        System.out.println("[6] - Endereço");

                        List<Pet> busca1;
                        List<Pet> busca2;
                        List<Pet> resultado;

                        try {
                            System.out.print("Primeiro Critério: ");
                            int criterio1 = sc.nextInt();
                            sc.nextLine();
                            if (criterio1 <= 0 || criterio1 > 6) {
                                throw new IllegalArgumentException("Digite um critério válido.");
                            }
                            busca1 = BuscarPet.buscar(tipo, criterio1);

                            System.out.print("Segundo Critério (pressione ENTER para ignorar): ");
                            String entrada = sc.nextLine();

                            if (entrada.isBlank()) {
                                System.out.println("Segundo critério ignorado.");
                                BuscarPet.exibirBusca(busca1);
                                break;
                            }
                            int criterio2 = Integer.parseInt(entrada);
                            if (criterio2 <= 0 || criterio2 > 6 || criterio2 == criterio1) {
                                throw new IllegalArgumentException("Digite um critério válido e diferente do primeiro.");
                            }
                            busca2 = BuscarPet.buscar(tipo, criterio2);
                            busca1.retainAll(busca2);
                            resultado = busca1;
                            BuscarPet.exibirBusca(resultado);
                            break;

                        } catch (NumberFormatException e) {
                            System.out.println("\nErro: Digite um critério válido, apenas números.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        } catch (InputMismatchException e) {
                            System.out.println("\nErro: Digite um critério válido, apenas números.");
                            sc.nextLine();
                        }
                    }
                    break;
                case 4:
                    BuscarPet.listarPetsCadastrados();
                    break;

                case 6:
                    System.out.println("Saindo...");
                    continua = false;
                    break;
            }
        }
    }
}

