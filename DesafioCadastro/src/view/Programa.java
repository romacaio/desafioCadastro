package view;

import model.entidades.Pet;
import model.io.RespostasFile;
import model.services.AlterarPet;
import model.services.BuscarPet;
import model.services.CadastrarPet;
import model.services.DeletarPet;
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
                String opStr = sc.nextLine().trim();
                op = Integer.parseInt(opStr);
                if (op <= 0 || op > 6) {
                    throw new IllegalArgumentException("Digite uma ação Válida.");

                }

            } catch (NumberFormatException e) {
                System.out.println("\nErro: Digite apenas números.");
            } catch (IllegalArgumentException e) {
                System.out.println("\nErro: " + e.getMessage());
            }
            switch (op) {
                case 1:
                    Pet petCadastrado = CadastrarPet.cadastrar(sc);
                    RespostasFile.criarRespostas(petCadastrado);
                    break;
                case 2:
                    AlterarPet.alterar(sc);
                    break;
                case 3:
                    DeletarPet.deletar(sc);
                    break;
                case 5:
                    BuscarPet.menuDeBuscaPorCriterios(sc);
                    break;
                case 4:
                    BuscarPet.listarPetsCadastrados();
                    break;

                case 6:
                    System.out.println("Saindo...");
                    continua = false;
                    sc.close();
                    break;
            }
        }
    }
}

