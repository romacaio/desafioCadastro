package controller;

import exceptions.*;
import model.*;
import repository.PetRepository;
import service.PetService;
import service.PetValidator;
import view.ConsoleView;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

public class PetController {
    private ConsoleView consoleView;
    private PetValidator petValidator;
    private PetRepository petRepository;
    private PetService petService;

    public PetController(ConsoleView consoleView, PetValidator petValidator, PetRepository petRepository, PetService petService) {
        this.consoleView = consoleView;
        this.petValidator = petValidator;
        this.petRepository = petRepository;
        this.petService = petService;
    }

    public void processarMenu() {
        int op = consoleView.exibirMenu();
        while (op != 6) {
            switch (op) {
                case 1 -> {
                    System.out.println("\n## CADASTRO ##");
                    Pet pet = cadastro();
                    try {
                        petRepository.salvarPet(pet);
                        System.out.println(pet.getNome() + "cadastrado no sistema com SUCESSO!");

                    } catch (FileNotFoundException | IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                }
                //case 2 ->
                case 3 -> {
                    try {
                        List<Pet> listaPets = petService.buscaPet(processarCriterio());
                        consoleView.exibirPets(listaPets);
                        System.out.println();
                        Pet petRemove = processarPetDelete(listaPets);
                        petRepository.excluirPet(petRemove);
                        System.out.println(petRemove.getNome() + " removido do sistema com SUCESSO!");

                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        consoleView.exibirPets();
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> {
                    try {
                        List<Pet> listaPets = petService.buscaPet(processarCriterio());
                        consoleView.exibirPets(listaPets);

                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case -1 -> System.out.println("Opção inválida! Apenas números são permitidos.");
                default -> System.out.println("Opção inválida! Digite um número correspondente a uma opção válida.");
            }
            System.out.println();
            op = consoleView.exibirMenu();
        }
        System.out.println("Programa encerrado...");
        consoleView.scClose();
        System.exit(0);
    }

    public Pet cadastro() {
        Pet newPet = new Pet();
        while (true) {
            try {
                String inputNome = consoleView.exibirPerguntaFormulario(0, false);
                String nome = petValidator.validaNome(inputNome);
                newPet.setNome(nome);

            } catch (NomeInvalidoException e) {
                System.out.println(e.getMessage() + "\n");
                continue;
            }
            break;
        }
        while (true) {
            try {
                String inputTipo = consoleView.exibirPerguntaFormulario(1, false);
                Tipo tipo = Tipo.parse(inputTipo);
                newPet.setTipo(tipo);

            } catch (TipoParseException e) {
                System.out.println(e.getMessage() + "\n");
                continue;
            }
            break;
        }
        while (true) {
            try {
                String inputSexo = consoleView.exibirPerguntaFormulario(2, false);
                Sexo sexo = Sexo.parse(inputSexo);
                newPet.setSexo(sexo);

            } catch (SexoParseException e) {
                System.out.println(e.getMessage() + "\n");
                continue;
            }
            break;
        }
        Endereco endereco = new Endereco();
        while (true) {
            try {
                consoleView.exibirPerguntaFormulario(3, true);
                String inputNumero = consoleView.exibirPergunta("Número: ");
                Integer numero = petValidator.ValidaNumero(inputNumero);
                endereco.setNumero(numero);

                String inputCidade = consoleView.exibirPergunta("Cidade: ");
                String cidade = petValidator.validaCidade(inputCidade);
                endereco.setCidade(cidade);

                String inputRua = consoleView.exibirPergunta("Rua: ");
                String rua = petValidator.validaRua(inputRua);
                endereco.setRua(rua);

                newPet.setEndereco(endereco);

            } catch (EnderecoInvalidoException e) {
                System.out.println(e.getMessage() + "\n");
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Numéro Inválido! Apenas dígitos são permitidos.\n");
                continue;
            }
            break;
        }
        while (true) {
            try {
                String inputIdade = consoleView.exibirPerguntaFormulario(4, false);
                Double idade = petValidator.validaIdade(inputIdade);
                newPet.setIdade(idade);

            } catch (IdadeInvalidaException e) {
                System.out.println(e.getMessage() + "\n");
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Idade inválida! Apenas números são permitidos.\n");
                continue;
            }
            break;
        }
        while (true) {
            try {
                String inputPeso = consoleView.exibirPerguntaFormulario(5, false);
                Double peso = petValidator.validaPeso(inputPeso);
                newPet.setPeso(peso);

            } catch (PesoInvalidoException e) {
                System.out.println(e.getMessage() + "\n");
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Peso inválido! Apenas números são permitidos.\n");
                continue;
            }
            break;
        }
        while (true) {
            try {
                String inputRaca = consoleView.exibirPerguntaFormulario(6, false);
                String raca = petValidator.validaRaca(inputRaca);
                newPet.setRaca(raca);

            } catch (RacaInvalidaException e) {
                System.out.println(e.getMessage() + "\n");
                continue;
            }
            break;
        }
        newPet.setDateCadastro(LocalDateTime.now());
        return newPet;
    }

    public CriterioBusca processarCriterio() {
        CriterioBusca criterioBusca = new CriterioBusca();

        while (true) {
            try {
                String inputTipo = consoleView.exibirPergunta("\nQual o TIPO do pet: ");
                Tipo tipo = Tipo.parse(inputTipo);
                criterioBusca.setTipoPet(tipo);

            } catch (TipoParseException e) {
                System.out.println(e.getMessage() + "\n");
                continue;
            }
            break;
        }
        while (true) {
            Boolean op = consoleView.isCombinarCriteriosBusca();
            if (op == null) {
                System.out.println("Opção inválida!\n");
                continue;
            } else if (!op) {
                return criterioBusca;
            }
            break;
        }

        for (int i = 0; i < 2; i++) {
            while (true) {
                int op = consoleView.exibirMenuCriterioBusca(i + 1);
                switch (op) {
                    case 1 -> {
                        while (true) {
                            String inputNome = consoleView.exibirPergunta("NOME do pet para busca: ");
                            try {
                                String nomeValidado = petValidator.validaApenasString(inputNome);
                                criterioBusca.setNomeOuSobrenome(PetService.analisaNaoInformado(String.valueOf(nomeValidado)));

                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage() + "\n");
                                continue;
                            }
                            break;
                        }
                    }
                    case 2 -> {
                        while (true) {
                            String inputSexo = consoleView.exibirPergunta("SEXO do pet para busca: ");
                            try {
                                Sexo sexo = Sexo.parse(inputSexo);
                                criterioBusca.setSexo(sexo);

                            } catch (SexoParseException e) {
                                System.out.println(e.getMessage() + "\n");
                                continue;
                            }
                            break;
                        }
                    }
                    case 3 -> {
                        while (true) {
                            String inputIdade = consoleView.exibirPergunta("IDADE do prt para busca: ");
                            try {
                                Double idadeValidada = petValidator.validaIdade(inputIdade);
                                criterioBusca.setIdade(PetService.analisaNaoInformado(String.valueOf(idadeValidada)));

                            } catch (IdadeInvalidaException e) {
                                System.out.println(e.getMessage() + "\n");
                                continue;
                            } catch (NumberFormatException e) {
                                System.out.println("Idade inválida! Apenas números são permitidos.\n");
                                continue;
                            }
                            break;
                        }
                    }
                    case 4 -> {
                        while (true) {
                            String inputPeso = consoleView.exibirPergunta("PESO do pet para busca: ");
                            try {
                                Double pesoValidado = petValidator.validaPeso(inputPeso);
                                criterioBusca.setPeso(PetService.analisaNaoInformado(String.valueOf(pesoValidado)));

                            } catch (PesoInvalidoException e) {
                                System.out.println(e.getMessage() + "\n");
                                continue;
                            } catch (NumberFormatException e) {
                                System.out.println("Peso inválido! Apenas números são permitidos.\n");
                                continue;
                            }
                            break;
                        }
                    }
                    case 5 -> {
                        while (true) {
                            String inputRaca = consoleView.exibirPergunta("RAÇA do pet para busca: ");
                            try {
                                String racaValidada = petValidator.validaRaca(inputRaca);
                                criterioBusca.setRaca(PetService.analisaNaoInformado(String.valueOf(racaValidada)));

                            } catch (RacaInvalidaException e) {
                                System.out.println(e.getMessage() + "\n");
                                continue;
                            }
                            break;
                        }
                    }
                    case 6 -> {
                        String inputEndereco = consoleView.exibirPergunta("ENDERECO do pet para busca (qualquer campo do endereço): ");
                        if (inputEndereco.isBlank()) {
                            inputEndereco = null;
                        }
                        criterioBusca.setEndereco(PetService.analisaNaoInformado(String.valueOf(inputEndereco)));
                    }
                    case 7 -> {
                        return criterioBusca;
                    }
                    case -1 -> {
                        System.out.println("Opção inválida! Apenas números são permitidos.\n");
                        continue;
                    }
                    default -> {
                        System.out.println("Opção inválida! Digite um número correspondente a um critério válido.\n");
                        continue;
                    }
                }
                break;
            }
        }
        return criterioBusca;
    }

    public Pet processarPetDelete(List<Pet> listaPets) {
        while (true) {
            int op = consoleView.exibirMenuDelete();
            if (op == -1) {
                System.out.println("Opção inválida! Apenas números são permitidos.\n");
                continue;
            } else if (op > listaPets.size()) {
                System.out.println("Opção inválida! Digite um número correspondente a um critério válido.\n");
                continue;
            }
            return listaPets.get(op - 1);
        }
    }
}