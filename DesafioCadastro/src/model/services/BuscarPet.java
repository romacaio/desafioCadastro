package model.services;

import model.entidades.Endereco;
import model.entidades.Pet;
import model.entidades.Sexo;
import model.entidades.Tipo;
import model.exceptions.IdadeInvalidaException;
import model.exceptions.PesoInvalidoException;
import model.exceptions.PetNomeInvalidoException;
import model.io.RespostasFile;
import model.util.TextoUtil;
import model.validadores.IdadeValidador;
import model.validadores.NomeValidador;
import model.validadores.PesoValidador;
import model.validadores.RacaValidador;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class BuscarPet {
    public static List<Pet> buscaPets = new ArrayList<>();

    public static void buscaPetsFiles() {
        File pasta = new File("petsCadastrados");
        File[] filesCadastro = pasta.listFiles();

        if (filesCadastro == null || filesCadastro.length == 0) {
            return;
        }

        for (int i = 0; i < filesCadastro.length; i++) {
            if (filesCadastro[i].getName().endsWith(".txt")) {
                try (BufferedReader br = new BufferedReader(new FileReader(filesCadastro[i]))) {

                    String nome = br.readLine().split("-")[1].trim();
                    if (nome.equals(Pet.NAO_INFORMADO)) {
                        nome = null;
                    }
                    String tipoStr = br.readLine().split("-")[1].trim();
                    Tipo tipo;
                    if (tipoStr.equals("CACHORRO")) {
                        tipo = Tipo.CACHORRO;
                    } else {
                        tipo = Tipo.GATO;
                    }
                    String sexoStr = br.readLine().split("-")[1].trim();
                    Sexo sexo;
                    if (sexoStr.equals("MACHO")) {
                        sexo = Sexo.MACHO;
                    } else {
                        sexo = Sexo.FEMEA;
                    }
                    String enderecoStr = br.readLine().split("-")[1].trim();
                    String idadeStr = br.readLine().split("-")[1].trim();
                    Double idade;
                    if (idadeStr.equals(Pet.NAO_INFORMADO)) {
                        idade = null;
                    } else if (idadeStr.contains("meses")) {
                        String mesesStr = idadeStr.replace("meses", "").trim();
                        double meses = Double.parseDouble(mesesStr);
                        idade = meses / 12;
                    } else {
                        String idadeSemAnos = idadeStr.replace("anos", "").trim();
                        idade = Double.parseDouble(idadeSemAnos);
                    }
                    String pesoStr = br.readLine().split("-")[1].replace("Kg", "").trim();
                    Double peso;
                    if (pesoStr.equals(Pet.NAO_INFORMADO)) {
                        peso = null;
                    } else {
                        peso = Double.parseDouble(pesoStr);
                    }
                    String raca = br.readLine().split("-")[1].trim();
                    if (raca.equals(Pet.NAO_INFORMADO)) {
                        raca = null;
                    }

                    String dateCadastroStr = br.readLine().split("-")[1].trim();
                    LocalDateTime dateCadastro = LocalDateTime.parse(dateCadastroStr, RespostasFile.padraoData);

                    String rua = enderecoStr.split(",")[0].replace("Rua", "").trim();
                    String numeroCasaStr = enderecoStr.split(",")[1].trim();
                    Integer numeroCasa;
                    if (numeroCasaStr.equals(Pet.NAO_INFORMADO)) {
                        numeroCasa = null;
                    } else {
                        numeroCasa = Integer.parseInt(numeroCasaStr);
                    }
                    String cidade = enderecoStr.split(",")[2].trim();

                    Endereco endereco = new Endereco(numeroCasa, cidade, rua);
                    Pet pet = new Pet(nome, tipo, sexo, endereco, idade, peso, raca);
                    pet.setDateCadastro(dateCadastro);

                    if (!petExiste(pet)) {
                        CadastrarPet.getPetsCadastrados().add(pet);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean petExiste(Pet pet) {
        List<Pet> petsCadastrados = CadastrarPet.getPetsCadastrados();
        for (Pet p : petsCadastrados) {
            boolean isNomesIguais = (pet.getNome() == null || p.getNome() == null) || pet.getNome().equalsIgnoreCase(p.getNome());
            boolean isTiposIguais = (pet.getTipo() == p.getTipo());
            boolean isSexosIguais = pet.getSexo() == p.getSexo();

            Endereco enderecop1 = pet.getEndereco();
            Endereco enderecop2 = p.getEndereco();

            boolean isRuasIguais = (enderecop1.getRua().equalsIgnoreCase(enderecop2.getRua()));
            boolean isCidadesIguais = (enderecop1.getCidade().equalsIgnoreCase(enderecop2.getCidade()));
            boolean isNumerosIguais = (enderecop1.getNumeroCasa() == null || enderecop2.getNumeroCasa() == null) || enderecop1.getNumeroCasa()
                    .equals(enderecop2.getNumeroCasa());

            if (isNomesIguais && isTiposIguais && isSexosIguais && isRuasIguais && isCidadesIguais && isNumerosIguais) {
                return true;
            }
        }
        return false;
    }

    public static void listarPetsCadastrados() {
        BuscarPet.buscaPetsFiles();
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
            if (pet.getNome() != null && nomePetNormalizado.contains(nomeBuscaNormalizado)) {
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
            if (pet.getIdade() != null && pet.getIdade() == idadeBusca) {
                resultado.add(pet);
            }
        }
        return resultado;
    }

    public static List<Pet> porPeso(String tipo, double pesoBusca) {
        List<Pet> listaFiltradaPorTipo = porTipo(tipo);
        List<Pet> resultado = new ArrayList<>();
        for (Pet pet : listaFiltradaPorTipo) {
            if (pet.getPeso() != null && pet.getPeso() == pesoBusca) {
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
            if (pet.getRaca() != null && racaPetNormalizada.equals(racaBuscaNormalizada)) {
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

    public static void menuDeBuscaPorCriterios(Scanner sc) {
        BuscarPet.buscaPetsFiles();
        String tipo;
        System.out.println("BUSCAR PET CADASTRADO");
        System.out.println("----------------------------------");
        while (true) {
            System.out.print("Primeiro, informe o TIPO de animal: ");
            try {
                Tipo tipoPet = Tipo.tipoPorNomeRelatorio(sc.nextLine().trim());
                if (tipoPet == null) {
                    throw new IllegalArgumentException("Tipo Inexistente.");
                }
                tipo = tipoPet.getNomeRelatorio();
                System.out.println();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("\nErro: " + e.getMessage());
            }
        }
        while (true) {
            System.out.println("Agora selecione os critérios de busca.");
            System.out.println("Você pode escolher até 2 critérios combinados:");
            System.out.println("[1] - Nome ou Sobrenome");
            System.out.println("[2] - Sexo");
            System.out.println("[3] - Idade");
            System.out.println("[4] - Peso");
            System.out.println("[5] - Raça");
            System.out.println("[6] - Endereço");

            List<Pet> busca1;
            List<Pet> busca2;
            List<Pet> resultado;

            try {
                System.out.print("Primeiro Critério: ");
                String entrada1 = sc.nextLine().trim();
                int criterio1 = Integer.parseInt(entrada1);
                if (criterio1 <= 0 || criterio1 > 6) {
                    throw new IllegalArgumentException("Digite um critério válido.");
                }
                busca1 = BuscarPet.buscar(tipo, criterio1);
                if (busca1.isEmpty()) {
                    BuscarPet.exibirBusca(busca1);
                    return;
                }

                System.out.print("Segundo Critério (pressione ENTER para ignorar): ");
                String entrada2 = sc.nextLine().trim();

                if (entrada2.isBlank()) {
                    System.out.println("Segundo critério ignorado.");
                    BuscarPet.exibirBusca(busca1);
                    break;
                }
                int criterio2 = Integer.parseInt(entrada2);
                if (criterio2 <= 0 || criterio2 > 6 || criterio2 == criterio1) {
                    throw new IllegalArgumentException("Digite um critério válido e diferente do primeiro.");
                }
                busca2 = BuscarPet.buscar(tipo, criterio2);
                if (busca2.isEmpty()) {
                    BuscarPet.exibirBusca(busca2);
                    return;
                }
                busca1.retainAll(busca2);
                resultado = busca1;
                BuscarPet.exibirBusca(resultado);
                if (resultado.isEmpty()) {
                    return;
                }
                break;

            } catch (NumberFormatException e) {
                System.out.println("\nErro: Digite um critério válido, apenas números.");
            } catch (IllegalArgumentException e) {
                System.out.println("\nErro: " + e.getMessage());
            }
        }
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
                        if (sexo == null) {
                            throw new IllegalArgumentException("Digite uma entrada válida, Fêmea ou Macho.");
                        }
                        String sexoStr = sexo.getNomeRelatorio();
                        resultado = porSexo(tipo, sexoStr);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro: " + e.getMessage());
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
                        System.out.println("\nErro: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("\nErro: Entrada inválida, digite apenas números.");
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
                        System.out.println("\nErro: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("\nErro: Entrada inválida, digite apenas números.");
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
                        System.out.println("\nErro: " + e.getMessage());
                    }
                }
                break;

            case 6:
                System.out.print("Endereço: ");
                String endereco = sc.nextLine().trim();
                resultado = porEndereco(tipo, endereco);
                break;
        }
        buscaPets = resultado;
        return resultado;
    }

    public static void exibirBusca(List<Pet> lista) {
        if (lista.isEmpty()) {
            System.out.println("\nNenhum Pet encontrado.");
            System.out.println();
            return;
        }
        System.out.println();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
        System.out.println();
    }

    public List<Pet> getBuscaPets() {
        return buscaPets;
    }
}
