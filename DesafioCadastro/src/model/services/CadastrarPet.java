package model.services;

import model.entidades.*;
import model.exceptions.EnderecoInvalidoException;
import model.exceptions.IdadeInvalidaException;
import model.exceptions.PesoInvalidoException;
import model.exceptions.PetNomeInvalidoException;
import model.io.FormularioFile;
import model.validadores.*;

import java.util.List;
import java.util.Scanner;

public class CadastrarPet {

    public static Pet cadastrar() {
        Scanner sc = new Scanner(System.in);
        List<String> linhas = FormularioFile.criarFormulario();
        Pet pet = new Pet();
        for (int i = 0; i < linhas.size(); i++) {
            switch (i) {
                case 0:
                    while (true) {
                        System.out.println(linhas.get(i));
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
                    break;
                case 1:
                    while (true) {
                        System.out.println(linhas.get(i));
                        try {
                            Tipo tipo = Tipo.tipoPorNomeRelatorio(sc.nextLine());
                            if (tipo == null) {
                                throw new IllegalArgumentException("Tipo Inexistente.");
                            }
                            pet.setTipo(tipo);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println(linhas.get(i));
                        try {
                            Sexo sexo = Sexo.sexoPorNomeRelatorio(sc.nextLine());
                            if (sexo == null) {
                                throw new IllegalArgumentException("Digite uma entrada válida, masculino ou feminino.");
                            }
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }
                    break;
                case 3:
                    Endereco endereco = new Endereco();
                    while (true) {
                        System.out.println(linhas.get(i));
                        try {
                            System.out.print("Número da casa: ");
                            String numeroStr = sc.nextLine();

                            if (Pet.isVazio(numeroStr)) {
                                endereco.setNumeroCasa(null);
                                break;
                            }
                            Integer numero = Integer.valueOf(numeroStr);
                            endereco.setNumeroCasa(numero);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Erro: Entrada inválida, digite apenas números.");
                        }
                    }
                    while (true) {
                        System.out.print("Cidade: ");
                        try {
                            String cidade = sc.nextLine();
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
                            String rua = sc.nextLine();
                            EnderecoValidador.validarEndereco(rua);
                            endereco.setRua(rua);

                            pet.setEndereco(endereco);
                            break;

                        } catch (EnderecoInvalidoException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }
                    break;
                case 4:
                    while (true) {
                        System.out.println(linhas.get(i));
                        try {
                            String idadeStr = sc.nextLine();
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
                            System.out.println("Erro: " + e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("Erro: Entrada inválida, digite apenas números.");
                        }
                    }
                    break;
                case 5:
                    while (true) {
                        System.out.println(linhas.get(i));
                        try {
                            String pesoStr = sc.nextLine();
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
                            System.out.println("Erro: " + e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("Erro: Entrada inválida, digite apenas números.");
                        }
                    }
                    break;
                case 6:
                    while (true) {
                        System.out.println(linhas.get(i));
                        try {
                            String raca = sc.nextLine();
                            if (Pet.isVazio(raca)) {
                                pet.setRaca(null);
                                break;
                            }
                            RacaValidador.validarRaca(raca);
                            pet.setRaca(raca);
                            break;

                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }
                    break;
            }
        }
        sc.close();
        return pet;
    }
}
