package model.services;

import model.entidades.Pet;
import model.entidades.Sexo;
import model.exceptions.IdadeInvalidaException;
import model.exceptions.PesoInvalidoException;
import model.exceptions.PetNomeInvalidoException;
import model.util.TextoUtil;
import model.validadores.IdadeValidador;
import model.validadores.NomeValidador;
import model.validadores.PesoValidador;
import model.validadores.RacaValidador;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BuscarPet {

    public static void listarPetsCadastrados() {
        List<Pet> petsCadastrados = CadastrarPet.getPetsCadastrados();
        if (petsCadastrados.isEmpty()) {
            System.out.println("Nenhum Pet cadastrado.");
            System.out.println();
            return;
        }
        for (int i = 0; i < petsCadastrados.size(); i++) {
            System.out.println((i + 1) + ". " + petsCadastrados.get(i));
        }
        System.out.println();
    }

    public static List<Pet> porTipo(String tipoBusca) {
        List<Pet> listaFiltradaPorTipo = new ArrayList<>();
        for (Pet pet : CadastrarPet.getPetsCadastrados()) {
            if (pet.getTipo().getNomeRelatorio().equals(tipoBusca)) {
                listaFiltradaPorTipo.add(pet);
            }
        }
        return listaFiltradaPorTipo;
    }

    public static List<Pet> porNome(String tipo, String nomeBusca) {
        String nomeBuscaNormalizado = TextoUtil.normalizar(nomeBusca); // remover os acentos
        List<Pet> listaFiltradaPorTipo = porTipo(tipo);
        List<Pet> resultado = new ArrayList<>();

        for (Pet pet : listaFiltradaPorTipo) {
            String nomePetNormalizado = TextoUtil.normalizar(pet.getNome());
            if (nomePetNormalizado.contains(nomeBuscaNormalizado)) {
                resultado.add(pet);
            }
        }
        return resultado;
    }

    public static List<Pet> porSexo(String tipo, String sexoBusca) {
        String sexoBuscaNormalizado = TextoUtil.normalizar(sexoBusca);
        List<Pet> listaFiltradaPorTipo = porTipo(tipo);
        List<Pet> resultado = new ArrayList<>();
        for (Pet pet : listaFiltradaPorTipo) {
            String sexoPetNormalizado = TextoUtil.normalizar(pet.getSexo().getNomeRelatorio());
            if (sexoPetNormalizado.equals(sexoBuscaNormalizado)) {
                resultado.add(pet);
            }
        }
        return resultado;
    }

    public static List<Pet> porIdade(String tipo, double idadeBusca) {
        List<Pet> listaFiltradaPorTipo = porTipo(tipo);
        List<Pet> resultado = new ArrayList<>();
        for (Pet pet : listaFiltradaPorTipo) {
            if (pet.getIdade() == idadeBusca) {
                resultado.add(pet);
            }
        }
        return resultado;
    }

    public static List<Pet> porPeso(String tipo, double pesoBusca) {
        List<Pet> listaFiltradaPorTipo = porTipo(tipo);
        List<Pet> resultado = new ArrayList<>();
        for (Pet pet : listaFiltradaPorTipo) {
            if (pet.getPeso() == pesoBusca) {
                resultado.add(pet);
            }
        }
        return resultado;
    }

    public static List<Pet> porRaca(String tipo, String racaBusca) {
        String racaBuscaNormalizada = TextoUtil.normalizar(racaBusca);
        List<Pet> listaFiltradaPorTipo = porTipo(tipo);
        List<Pet> resultado = new ArrayList<>();
        for (Pet pet : listaFiltradaPorTipo) {
            String racaPetNormalizada = TextoUtil.normalizar(pet.getRaca());
            if (racaPetNormalizada.equals(racaBuscaNormalizada)) {
                resultado.add(pet);
            }
        }
        return resultado;
    }

    public static List<Pet> porEndereco(String tipo, String enderecoBusca) {
        String enderecoBuscaNormalizado = TextoUtil.normalizar(enderecoBusca);
        List<Pet> listaFiltradaPorTipo = porTipo(tipo);
        List<Pet> resultado = new ArrayList<>();
        for (Pet pet : listaFiltradaPorTipo) {
            String enderecoPetNormalizado = TextoUtil.normalizar(pet.getEndereco().toString());
            if (enderecoPetNormalizado.contains(enderecoBuscaNormalizado)) {
                resultado.add(pet);
            }

        }
        return resultado;
    }

    public static List<Pet> buscar(String tipo, int criterio) {
        Scanner sc = new Scanner(System.in);
        List<Pet> resultado = new ArrayList<>();

        switch (criterio) {
            case 1:
                while (true) {
                    System.out.print("Nome pet: ");
                    try {
                        String nome = sc.nextLine().trim();
                        NomeValidador.validarNomeBusca(nome);
                        resultado = porNome(tipo, nome);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\nErro: Apenas letras é permitido.");
                    } catch (PetNomeInvalidoException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }
                }
                break;

            case 2:
                while (true) {
                    System.out.print("Sexo pet: ");
                    try {
                        Sexo sexo = Sexo.sexoPorNomeRelatorio(sc.nextLine().trim());
                        String sexoStr = sexo.getNomeRelatorio();
                        resultado = porSexo(tipo, sexoStr);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                break;

            case 3:
                while (true) {
                    System.out.print("Idade pet: ");
                    try {
                        String idadeStr = sc.nextLine().trim();
                        idadeStr = idadeStr.replace(",", ".");
                        Double idade = Double.valueOf(idadeStr);
                        IdadeValidador.validarIdade(idade);
                        resultado = porIdade(tipo, idade);
                        break;

                    } catch (IdadeInvalidaException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Entrada inválida, digite apenas números.");
                    }
                }
                break;

            case 4:
                while (true) {
                    System.out.print("Peso pet: ");
                    try {
                        String pesoStr = sc.nextLine().trim();
                        pesoStr = pesoStr.replace(",", ".");
                        Double peso = Double.valueOf(pesoStr);
                        PesoValidador.validarPeso(peso);
                        resultado = porPeso(tipo, peso);
                        break;
                    } catch (PesoInvalidoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Entrada inválida, digite apenas números.");
                    }
                }
                break;

            case 5:
                while (true) {
                    System.out.print("Raça pet: ");
                    try {
                        String raca = sc.nextLine().trim();
                        RacaValidador.validarRaca(raca);
                        resultado = porRaca(tipo, raca);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                break;

            case 6:
                System.out.print("Endereço: ");
                String endereco = sc.nextLine().trim();
                resultado = porEndereco(tipo, endereco);
                break;
        }
        return resultado;
    }

    public static void exibirBusca(List<Pet> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhum pet encontrado.");
            System.out.println();
            return;
        }
        System.out.println();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
        System.out.println();
    }
}
