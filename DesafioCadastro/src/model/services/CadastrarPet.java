package model.services;

import model.entidades.*;
import model.exceptions.PetNomeInvalidoException;
import model.validadores.PetValidador;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CadastrarPet {

    public static Pet cadastrar() {
        Scanner sc = new Scanner(System.in);
        List<String> linhas = File.criarFormulario();
        Pet pet = new Pet();
        for (int i = 0; i < linhas.size(); i++) {
            switch (i) {
                case 0:
                    while (true) {
                        System.out.println(i);
                        try {
                            String nome = sc.nextLine();
                            if (Pet.isVazio(nome)) {
                                pet.setNome(null);
                                break;
                            }
                            PetValidador.validarNome(nome);
                            pet.setNome(nome);
                            break;
                        } catch (PetNomeInvalidoException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }
                case 1:
                    while (true) {
                        System.out.println(i);
                        try {
                            Tipo tipo = Tipo.tipoPorNomeRelatorio(sc.nextLine());
                            if (tipo == null) {
                                throw new IllegalArgumentException("Tipo Inexistente.");
                            }
                            pet.setTipo(tipo);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro " + e.getMessage());
                        }
                    }
                case 2:
                    while (true) {
                        System.out.println(i);
                        try {
                            Sexo sexo = Sexo.sexoPorNomeRelatorio(sc.nextLine());
                            if (sexo == null) {
                                throw new IllegalArgumentException("Digite uma entrada válida.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }
                case 3:
                    while (true) {
                        System.out.println(i);
                        try {
                            System.out.print("Número da casa: ");
                            String numerostr = sc.nextLine();
                            Integer numero;
                            if (Pet.isVazio(numerostr)) {
                                numero = null;
                                break;
                            }
                            numero = Integer.valueOf(numerostr);
                            pet.getEndereco().setNumeroCasa(numero);

                            System.out.print("Cidade: ");
                            String cidade = sc.nextLine();
                            System.out.print("Rua: ");
                            String rua = sc.nextLine();
                            Endereco endereco = new Endereco(numero, cidade, rua);
                            pet.setEndereco(endereco);
                            break;
                        } catch (InputMismatchException e) {

                        }

                    }
            }
        }
    }
}
