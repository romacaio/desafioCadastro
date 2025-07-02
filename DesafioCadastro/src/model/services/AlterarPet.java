package model.services;

import model.entidades.Pet;
import model.exceptions.EnderecoInvalidoException;
import model.exceptions.IdadeInvalidaException;
import model.exceptions.PesoInvalidoException;
import model.exceptions.PetNomeInvalidoException;
import model.io.RespostasFile;
import model.validadores.*;
import java.util.Locale;
import java.util.Scanner;

public class AlterarPet {
    public static void alterar(Scanner sc) {
        BuscarPet.buscaPets.clear();
        BuscarPet.menuDeBuscaPorCriterios(sc);
        if (BuscarPet.buscaPets.isEmpty()) {
            return;
        }
        int numero = 0;
        while (true) {
            System.out.println("Digite o número correspondente ao pet que você deseja alterar");
            System.out.print("Número do pet: ");
            try {
                String numeroStr = sc.nextLine().trim();
                numero = Integer.parseInt(numeroStr);
                if (numero < 1 || numero > BuscarPet.buscaPets.size()) {
                    throw new IllegalArgumentException("Digite um número correspondente válido.");
                }
                System.out.println();
                break;
            } catch (NumberFormatException e) {
                System.out.println("\nErro: Digite apenas números.");
            } catch (IllegalArgumentException e) {
                System.out.println("\nErro: " + e.getMessage());
            }
        }

        Boolean continua = true;
        while (continua) {
            System.out.println("ALTERAR DADOS DO PET");
            System.out.println("----------------------------");
            System.out.println("[1] - Nome");
            System.out.println("[2] - Endereço");
            System.out.println("[3] - Idade");
            System.out.println("[4] - Peso");
            System.out.println("[5] - Raça");
            System.out.println("[6] - Voltar ao Menu principal");
            System.out.print("Selecione o que deseja alterar: ");

            int op = 0;

            try {
                String opStr = sc.nextLine().trim();
                op = Integer.parseInt(opStr);
                if (op > 6 || op < 1) {
                    throw new IllegalArgumentException("Digite uma ação válida.");
                }
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("\nErro: Digite apenas números.");
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println("\nErro: " + e.getMessage());
                continue;
            }
            Pet petSelecionado = BuscarPet.buscaPets.get(numero - 1);
            switch (op) {
                case 1:
                    if (petSelecionado.getEndereco().getNumeroCasa() == null) {
                        System.out.println("O nome atual do Pet selecionado é: " + Pet.NAO_INFORMADO);
                    } else {
                        System.out.println("O nome atual do Pet selecionado é: " + petSelecionado.getNome());
                    }
                    while (true) {
                        System.out.print("Informe o novo nome que deseja atribuir: ");
                        try {
                            String novoNome = sc.nextLine().trim();
                            NomeValidador.validarNomeCadastro(novoNome);
                            petSelecionado.setNome(novoNome);
                            RespostasFile.excluirFile(petSelecionado);
                            RespostasFile.criarRespostas(petSelecionado);
                            System.out.println("Nome do Pet alterado com sucesso!");
                            System.out.println();
                            break;
                        } catch (PetNomeInvalidoException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Endereço atual do Pet selecionado:");
                    if (petSelecionado.getEndereco().getNumeroCasa() == null) {
                        System.out.println("Número da casa: " + Pet.NAO_INFORMADO);
                    } else {
                        System.out.println("Número da casa: " + petSelecionado.getEndereco().getNumeroCasa());
                    }
                    System.out.println("Rua: " + petSelecionado.getEndereco().getRua());
                    System.out.println("Cidade: " + petSelecionado.getEndereco().getCidade());
                    System.out.print("Digite o novo número da casa: ");
                    while (true) {
                        try {
                            String numeroCasaStr = sc.nextLine().trim();
                            int numeroCasa = Integer.parseInt(numeroCasaStr);
                            petSelecionado.getEndereco().setNumeroCasa(numeroCasa);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("\nErro: Digite um valor válido, apenas números são permitidas.");
                        }
                    }
                    while (true) {
                        System.out.print("Digite a nova rua: ");
                        try {
                            String ruaStr = sc.nextLine().trim();
                            EnderecoValidador.validarEndereco(ruaStr);
                            petSelecionado.getEndereco().setRua(ruaStr);
                            break;
                        } catch (EnderecoInvalidoException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                    }
                    while (true) {
                        System.out.print("Digite a nova cidade: ");
                        try {
                            String cidadeStr = sc.nextLine().trim();
                            EnderecoValidador.validarEndereco(cidadeStr);
                            petSelecionado.getEndereco().setCidade(cidadeStr);
                            RespostasFile.excluirFile(petSelecionado);
                            RespostasFile.criarRespostas(petSelecionado);
                            System.out.println("Endereço do Pet alterado com sucesso!");
                            System.out.println();
                            break;
                        } catch (EnderecoInvalidoException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                    }
                    break;
                case 3:
                    if (petSelecionado.getIdade() == null) {
                        System.out.println("Idade atual do Pet selecionado: " + Pet.NAO_INFORMADO);
                    } else if (petSelecionado.getIdade() < 1) {
                        int meses = (int) (petSelecionado.getIdade() * 12);
                        System.out.println("Idade atual do Pet selecionado: " + meses + " meses");
                    } else {
                        System.out.println("Idade atual do Pet selecionado: " + petSelecionado.getIdade() + " anos");
                    }
                    while (true) {
                        System.out.print("Digite a nova idade do Pet selecionado: ");
                        try {
                            String idadeStr = sc.nextLine().trim().replace(",", ".");
                            double idade = Double.parseDouble(idadeStr);
                            IdadeValidador.validarIdade(idade);
                            petSelecionado.setIdade(idade);
                            RespostasFile.excluirFile(petSelecionado);
                            RespostasFile.criarRespostas(petSelecionado);
                            System.out.println("Idade do Pet alterado com sucesso!");
                            System.out.println();
                            break;

                        } catch (IdadeInvalidaException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("\nErro: Digite apenas números.");
                        }
                    }
                    break;
                case 4:
                    if (petSelecionado.getPeso() == null) {
                        System.out.println("Peso atual do Pet selecionado: " + Pet.NAO_INFORMADO);
                    } else {
                        System.out.println("Peso atual do Pet selecionado: " + String.format(Locale.US, "%.1f", petSelecionado.getPeso()) + " Kg");
                    }
                    System.out.print("Digite o novo peso do Pet selecionado: ");
                    while (true) {
                        try {
                            String pesoStr = sc.nextLine().trim().replace(",", ".");
                            double peso = Double.parseDouble(pesoStr);
                            PesoValidador.validarPeso(peso);
                            petSelecionado.setPeso(peso);
                            RespostasFile.excluirFile(petSelecionado);
                            RespostasFile.criarRespostas(petSelecionado);
                            System.out.println("Peso do Pet alterado com sucesso!");
                            System.out.println();
                            break;
                        } catch (PesoInvalidoException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("\nErro: Digite apenas números.");
                        }
                    }
                    break;
                case 5:
                    if (petSelecionado.getRaca() == null) {
                        System.out.println("Raça atual do Pet selecionado: " + Pet.NAO_INFORMADO);
                    } else {
                        System.out.println("Raça atual do Pet selecionado: " + petSelecionado.getRaca());
                    }
                    while (true) {
                        System.out.print("Digite a nova raca do Pet: ");
                        try {
                            String raca = sc.nextLine().trim();
                            RacaValidador.validarRaca(raca);
                            petSelecionado.setRaca(raca);
                            RespostasFile.excluirFile(petSelecionado);
                            RespostasFile.criarRespostas(petSelecionado);
                            System.out.println("Raça do Pet alterada com sucesso!");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                    }
                    break;
                case 6:
                    continua = false;
                    System.out.println("Voltando ao Menu Principal...");
                    System.out.println();
                    break;
            }
        }
    }
}
