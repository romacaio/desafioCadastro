package repository;

import model.Endereco;
import model.Pet;
import model.Sexo;
import model.Tipo;
import service.PetService;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PetRepository {
    private static final File file = new File("src\\repository\\petsCadastrados");
    private PetService petService;

    public PetRepository(PetService petService) {
        this.petService = petService;
    }

    public void petsCadastradosCreated() {
        if (!file.exists()) {
            new File("src\\repository\\petsCadastrados").mkdir();
        }
    }

    public void salvarPet(Pet pet) {
        petsCadastradosCreated();

        File filePet = null;
        try {
            filePet = criaFilePet(pet);
            filePet.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePet))) {

            String nome = petService.analisaNaoInformado(String.valueOf(pet.getNome()));
            bw.write("1 - " + nome);
            bw.newLine();

            bw.write("2 - " + pet.getTipo().getNomeUsual());
            bw.newLine();
            bw.write("3 - " + pet.getSexo().getNomeUsual());
            bw.newLine();

            String rua = pet.getEndereco().getRua();
            String numero = petService.analisaNaoInformado(String.valueOf(pet.getEndereco().getNumero()));
            String cidade = pet.getEndereco().getCidade();
            bw.write("4 - " + rua + ", " + numero + ", " + cidade);
            bw.newLine();

            String idade = pet.getIdadeFormatada();
            bw.write("5 - " + idade);
            bw.newLine();

            String peso = petService.analisaNaoInformado(String.valueOf(pet.getPeso()));
            bw.write("6 - " + peso + (peso.equals(PetService.NAO_INFORMADO) ? "" : "Kg"));
            bw.newLine();

            String raca = petService.analisaNaoInformado(pet.getRaca());
            bw.write("7 - " + raca);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File criaFilePet(Pet pet) {
        String patchDiretorio = file + "\\";
        String nomeFormatado = "-" + pet.getNome().toUpperCase().replace(" ", "").concat(".txt");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");

        return new File(patchDiretorio + dtf.format(LocalDateTime.now()) + nomeFormatado);
    }

    public List<Pet> carregarPetsFile() throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("Nenhum pet ainda foi cadastrado no sistema.");
        }

        List<Pet> petsCadastrados = new ArrayList<>();
        File[] filesPets = file.listFiles();
        if (filesPets.length == 0) {
            throw new FileNotFoundException("Nenhum pet ainda foi cadastrado no sistema.");
        }

        for (File filePet : filesPets) {
            if (filePet.isFile() && filePet.getName().endsWith(".txt")) {
                petsCadastrados.add(lerArquivo(filePet));
            }
        }
        return petsCadastrados;
    }


    public Pet lerArquivo(File file) {
        Pet pet = new Pet();
        List<String> respostas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                respostas.add(petService.extraiDadoFile(linha));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        pet.setNome(petService.analisaNaoInformado(respostas.get(0)));
        pet.setTipo(Tipo.parse(respostas.get(1)));
        pet.setSexo(Sexo.parse(respostas.get(2)));

        Endereco endereco = new Endereco();
        String[] camposEndereco = respostas.get(3).split(",");
        endereco.setRua(camposEndereco[0].trim());
        String numero = petService.analisaNaoInformado(camposEndereco[1]);
        if (numero == null) {
            endereco.setNumero(null);
        } else {
            endereco.setNumero(Integer.parseInt(numero));
        }
        endereco.setCidade(camposEndereco[2].trim());
        pet.setEndereco(endereco);

        String idade = petService.analisaNaoInformado(respostas.get(4));
        if (idade == null) {
            pet.setIdade(null);
        } else {
            pet.setIdade(Double.parseDouble(idade));
        }

        String peso = petService.analisaNaoInformado(respostas.get(5));
        if (peso == null) {
            pet.setPeso(null);
        } else {
            pet.setPeso(Double.parseDouble(peso));
        }

        pet.setRaca(petService.analisaNaoInformado(respostas.get(6)));
        return pet;
    }
}
