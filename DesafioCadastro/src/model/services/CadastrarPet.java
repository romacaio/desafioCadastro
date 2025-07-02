package model.services;

import model.entidades.*;
import model.exceptions.EnderecoInvalidoException;
import model.exceptions.IdadeInvalidaException;
import model.exceptions.PesoInvalidoException;
import model.exceptions.PetNomeInvalidoException;
import model.io.FormularioFile;
import model.validadores.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastrarPet {
    private static List<Pet> petsCadastrados = new ArrayList<>();

    public static Pet cadastrar(Scanner sc) {

        List<String> linhas = FormularioFile.criarFormulario();
        Pet pet = new Pet();
        for (int i = 0; i < linhas.size(); i++) {
            switch (i) {
                case 0:
                    while (true) {
                        System.out.print(linhas.get(i) + " ");
                        try {
                            String nome = sc.nextLine().trim();
                            if (Pet.isVazio(nome)) {
                                pet.setNome(null);
                                break;
                            }
                            NomeValidador.validarNomeCadastro(nome);
                            pet.setNome(nome);
                            break;
                        } catch (PetNomeInvalidoException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                    }
                    break;
                case 1:
                    while (true) {
                        System.out.print(linhas.get(i) + " ");
                        try {
                            Tipo tipo = Tipo.tipoPorNomeRelatorio(sc.nextLine().trim());
                            if (tipo == null) {
                                throw new IllegalArgumentException("Tipo Inexistente.");
                            }
                            pet.setTipo(tipo);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.print(linhas.get(i) + " ");
                        try {
                            Sexo sexo = Sexo.sexoPorNomeRelatorio(sc.nextLine().trim());
                            if (sexo == null) {
                                throw new IllegalArgumentException("Digite uma entrada válida, (Fêmea/Macho).");
                            }
                            pet.setSexo(sexo);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                    }
                    break;
                case 3:
                    Endereco endereco = new Endereco();
                    while (true) {
                        System.out.print(linhas.get(i));
                        try {
                            System.out.print("\nNúmero da casa: ");
                            String numeroStr = sc.nextLine().trim();

                            if (Pet.isVazio(numeroStr)) {
                                endereco.setNumeroCasa(null);
                                break;
                            }
                            Integer numero = Integer.valueOf(numeroStr);
                            endereco.setNumeroCasa(numero);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("\nErro: Entrada inválida, digite apenas números.");
                        }
                    }
                    while (true) {
                        System.out.print("Cidade: ");
                        try {
                            String cidade = sc.nextLine().trim();
                            EnderecoValidador.validarEndereco(cidade);
                            endereco.setCidade(cidade);
                            break;

                        } catch (EnderecoInvalidoException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }
                    while (true) {
                        System.out.print("Rua: ");
                        try {
                            String rua = sc.nextLine().trim();
                            EnderecoValidador.validarEndereco(rua);
                            endereco.setRua(rua);

                            pet.setEndereco(endereco);
                            break;

                        } catch (EnderecoInvalidoException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                    }
                    break;
                case 4:
                    while (true) {
                        System.out.print(linhas.get(i) + " ");
                        try {
                            String idadeStr = sc.nextLine().trim();
                            idadeStr = idadeStr.replace(",", ".");
                            if (Pet.isVazio(idadeStr)) {
                                pet.setIdade(null);
                                break;
                            }
                            Double idade = Double.valueOf(idadeStr);
                            IdadeValidador.validarIdade(idade);

                            pet.setIdade(idade);
                            break;

                        } catch (IdadeInvalidaException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("\nErro: Entrada inválida, digite apenas números.");
                        }
                    }
                    break;
                case 5:
                    while (true) {
                        System.out.print(linhas.get(i) + " ");
                        try {
                            String pesoStr = sc.nextLine().trim();
                            pesoStr = pesoStr.replace(",", ".");
                            if (Pet.isVazio(pesoStr)) {
                                pet.setPeso(null);
                                break;
                            }
                            Double peso = Double.valueOf(pesoStr);
                            PesoValidador.validarPeso(peso);

                            pet.setPeso(peso);
                            break;

                        } catch (PesoInvalidoException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("\nErro: Entrada inválida, digite apenas números.");
                        }
                    }
                    break;
                case 6:
                    while (true) {
                        System.out.print(linhas.get(i) + " ");
                        try {
                            String raca = sc.nextLine().trim();
                            if (Pet.isVazio(raca)) {
                                pet.setRaca(null);
                                System.out.println();
                                break;
                            }
                            RacaValidador.validarRaca(raca);
                            pet.setRaca(raca);
                            System.out.println();
                            break;

                        } catch (IllegalArgumentException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                    }
                    break;
            }
        }
        pet.setDateCadastro(LocalDateTime.now());
        petsCadastrados.add(pet);
        return pet;
    }

    public static List<Pet> getPetsCadastrados() {
        return petsCadastrados;
    }
}
