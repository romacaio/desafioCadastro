package controller;

import exceptions.*;
import model.Endereco;
import model.Pet;
import model.Sexo;
import model.Tipo;
import repository.PetRepository;
import service.PetValidator;
import view.ConsoleView;

public class PetController {
    private ConsoleView consoleView;
    private PetValidator petValidator;
    private PetRepository petRepository;

    public PetController(ConsoleView consoleView, PetValidator petValidator, PetRepository petRepository) {
        this.consoleView = consoleView;
        this.petValidator = petValidator;
        this.petRepository = petRepository;
    }

    public void processarMenu() {
        int op = consoleView.exibirMenu();
        while (op != 6) {

            switch (op) {
                case 1 -> {
                    System.out.println("\n## CADASTRO ##");
                    petRepository.salvarPet(cadastro());
                }
                //case 2 ->
                //case 3 ->
                //case 4 ->
                //case 5 ->
                case -1 -> System.out.println("Opção inválida! Apenas números são permitidos.\n");
                default -> System.out.println("Opção inválida! Digite um número correspondente a uma opção válida.\n");
            }
            op = consoleView.exibirMenu();
        }
        System.out.println("Programa encerrado...");
        System.exit(0);
    }

    public Pet cadastro() {
        Pet newPet = new Pet();
        while (true) {
            try {
                String inputNome = consoleView.exibirPergunta(0, false);
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
                String inputTipo = consoleView.exibirPergunta(1, false);
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
                String inputSexo = consoleView.exibirPergunta(2, false);
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
                consoleView.exibirPergunta(3, true);
                String inputNumero = consoleView.exibirPerguntaEndereco("Número: ");
                Integer numero = petValidator.ValidaNumero(inputNumero);
                endereco.setNumero(numero);

                String inputCidade = consoleView.exibirPerguntaEndereco("Cidade: ");
                String cidade = petValidator.validaCidade(inputCidade);
                endereco.setCidade(cidade);

                String inputRua = consoleView.exibirPerguntaEndereco("Rua: ");
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
                String inputIdade = consoleView.exibirPergunta(4, false);
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
                String inputPeso = consoleView.exibirPergunta(5, false);
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
                String inputRaca = consoleView.exibirPergunta(6, false);
                String raca = petValidator.validaRaca(inputRaca);
                newPet.setRaca(raca);

            } catch (RacaInvalidaException e) {
                System.out.println(e.getMessage() + "\n");
                continue;
            }
            break;
        }
        return newPet;
    }
}