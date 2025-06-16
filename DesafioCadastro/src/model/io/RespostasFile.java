package model.io;

import model.entidades.Pet;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RespostasFile {

    public static void criarRespostas(Pet pet) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        LocalDateTime now = LocalDateTime.now();
        String dataFormatada = now.format(dtf);
        String nomePet = pet.getNome().toUpperCase();

        File pasta = new File("petsCadastrados");
        pasta.mkdir();
        File respostasFile = new File(pasta, dataFormatada.concat("-" + nomePet + ".txt"));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(respostasFile))) {
            if (pet.getNome() == null) {
                bw.write("1 - " + Pet.NAO_INFORMADO);
            } else {
                bw.write("1 - " + pet.getNome());
            }

            bw.newLine();
            bw.write("2 - " + pet.getTipo());

            bw.newLine();
            bw.write("3 - " + pet.getSexo());

            bw.newLine();
            bw.write("4 - " + pet.getEndereco());

            bw.newLine();
            if (pet.getIdade() < 1) {
                int meses = (int) (pet.getIdade() * 12);
                bw.write("5 - " + meses + " meses");
            } else {
                bw.write("5 - " + String.format("%.1f", pet.getIdade()) + " anos");
            }

            bw.newLine();
            if (pet.getPeso() == null) {
                bw.write("6 - " + Pet.NAO_INFORMADO);
            } else {
                bw.write("6 - " + pet.getPeso() + "Kg");
            }

            bw.newLine();
            if (pet.getRaca() == null) {
                bw.write("7 - " + Pet.NAO_INFORMADO);
            } else {
                bw.write("7 - " + pet.getRaca());
            }

            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
