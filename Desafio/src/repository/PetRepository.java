package repository;

import model.Pet;
import service.PetService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetRepository {
    private static final File file = new File("src\\repository\\petsCadastrados");
    private PetService petService;

    public PetRepository(PetService petService) {
        this.petService = petService;
    }

    public void salvarPet(Pet pet) {
        if (!file.exists()) {
            new File("src\\repository\\petsCadastrados").mkdir();

        }
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
            String cidade = pet.getEndereco().getCidade();
            String numero = petService.analisaNaoInformado(String.valueOf(pet.getEndereco().getNumero()));
            bw.write("4 - " + rua + ", " + numero + ", " + cidade);
            bw.newLine();

            String idade = petService.analisaIdade(String.valueOf(pet.getIdade()));
            bw.write("5 - " + idade);
            bw.newLine();

            String peso = petService.analisaNaoInformado(String.valueOf(pet.getPeso()));
            bw.write("6 - " + peso + (peso.equals("não informado") ? "" : "Kg"));
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
}
