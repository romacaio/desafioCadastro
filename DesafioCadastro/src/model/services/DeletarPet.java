package model.services;

import model.entidades.Pet;
import model.io.RespostasFile;
import model.util.TextoUtil;

import java.util.List;
import java.util.Scanner;

public class DeletarPet {
    public static void deletar(Scanner sc) {
        BuscarPet.menuDeBuscaPorCriterios(sc);
        List<Pet> busca = BuscarPet.buscaPets;
        System.out.println("Digite o número correspondente ao pet que você deseja Deletar: ");
        System.out.print("Número do pet: ");

        int numero = 0;
        try {
            String numeroStr = sc.nextLine();
            numero = Integer.parseInt(numeroStr);
            if (numero < 1 || numero > busca.size()) {
                throw new IllegalArgumentException("Digite um número correspondente válido.");
            }
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("\nErro: Digite apenas números.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
        Pet petSelecionado = BuscarPet.buscaPets.get(numero - 1);
        while (true) {
            System.out.print("Deseja realmte excluir o Pet (Sim/Não): ");
            String resposta = sc.nextLine();
            String respostaNormalizada = TextoUtil.normalizar(resposta);
            if (respostaNormalizada.equals("sim")) {
                CadastrarPet.getPetsCadastrados().remove(petSelecionado);
                RespostasFile.excluirFile(petSelecionado);
                System.out.println("O Pet foi removido com sucesso!");
                System.out.println();
                break;
            } else if (respostaNormalizada.equals("nao")) {
                System.out.println("O Pet não foi removido.");
                System.out.println();
                break;
            } else {
                System.out.println("Digite uma resposta válida.");
                System.out.println();
            }

        }

    }
}
