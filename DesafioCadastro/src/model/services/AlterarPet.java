package model.services;

import model.entidades.Pet;
import model.exceptions.IdadeInvalidaException;
import model.exceptions.PesoInvalidoException;
import model.exceptions.PetNomeInvalidoException;
import model.validadores.IdadeValidador;
import model.validadores.NomeValidador;
import model.validadores.PesoValidador;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AlterarPet {
    public static void alterar(Scanner sc, String tipo, int criterio) {
        List<Pet> busca = BuscarPet.buscar(tipo, criterio);
        BuscarPet.exibirBusca(busca);
        System.out.println("Digite o número correspondente ao pet que você deseja alterar:");
        System.out.print("Número do pet: ");
        try {
            String numeroStr = sc.nextLine();
            int numero = Integer.parseInt(numeroStr);
            if (numero > busca.size() || numero < busca.size()) {
                throw new IllegalArgumentException("Digite um número correspondente válido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nErro: Digite apenas números.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
        while (true) {
            System.out.println("ALTERAR DADOS DO PET");
            System.out.println("----------------------------");
            System.out.println("[1] - Nome");
            System.out.println("[2] - Endereço");
            System.out.println("[3] - Idade");
            System.out.println("[4] - Peso");
            System.out.println("[5] - Raça");
            System.out.println("[6] - Alterar TODOS os dados acima");
            System.out.print("Selecione o que deseja alterar: ");

            int op = 0;
            Pet pet = null;
            try {
                String opStr = sc.nextLine();
                op = Integer.parseInt(opStr);
                if (op > 6 || op < 1) {
                    throw new IllegalArgumentException("Digite uma ação válida.");
                }
                pet = CadastrarPet.getPetsCadastrados().get(op - 1);
            } catch (NumberFormatException e) {
                System.out.println("\nErro: Digite apenas números.");
            } catch (IllegalArgumentException e) {
                System.out.println("\nErro: " + e.getMessage());
            }
            switch (op) {
                case 1:
                    while (true) {
                        System.out.println("O nome atual do pet é: " + pet.getNome());
                        System.out.print("Informe o novo nome que deseja atribuir: ");
                        try {
                            String novoNome = sc.nextLine().trim();
                            NomeValidador.validarNomeCadastro(novoNome);
                            pet.setNome(novoNome);
                            System.out.println("Nome do Pet alterado com sucesso!");
                            System.out.println();
                            break;
                        } catch (PetNomeInvalidoException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        if (pet.getIdade() == null) {
                            System.out.println("Idade atual do pet: " + Pet.NAO_INFORMADO);
                        } else if (pet.getIdade() < 1) {
                            int meses = (int) (pet.getIdade() * 12);
                            System.out.println("Idade atual do pet: " + meses + " meses");
                        } else {
                            System.out.println("Idade atual do pet: " + pet.getIdade() + " anos");
                        }
                        System.out.print("Digite a nova idade do pet: ");
                        try {
                            String idadeStr = sc.nextLine().trim().replace(",", ".");
                            double idade = Double.parseDouble(idadeStr);
                            IdadeValidador.validarIdade(idade);
                            pet.setIdade(idade);
                            break;

                        } catch (IdadeInvalidaException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("\nErro: Digite apenas números.");
                        }
                    }
                    break;
                case 4:
                    while (true) {
                        if (pet.getPeso() == null) {
                            System.out.println("Peso atual do pet: " + Pet.NAO_INFORMADO);
                        } else {
                            System.out.println("Peso atual do pet: " + String.format(Locale.US, "%.1f", pet.getPeso()) + " Kg");
                        }
                        System.out.print("Digite o novo peso do pet: ");
                        try {
                            String pesoStr = sc.nextLine().trim().replace(",", ".");
                            double peso = Double.parseDouble(pesoStr);
                            PesoValidador.validarPeso(peso);
                            pet.setPeso(peso);
                            break;
                        } catch (PesoInvalidoException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("\nErro: Digite apenas números.");
                        }
                    }
                    break;
                case 5:
                    while (true){
                        if(pet.getSexo() == null)
                    }

            }
        }
    }
}
