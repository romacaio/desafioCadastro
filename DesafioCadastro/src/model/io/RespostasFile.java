package model.io;

import model.entidades.Pet;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RespostasFile {
    public static final DateTimeFormatter padraoData = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");

    public static void criarRespostas(Pet pet) {
        File respostaFile = atualizarNomeFile(pet);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(respostaFile))) {
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
            if (pet.getIdade() == null) {
                bw.write("5 - " + Pet.NAO_INFORMADO);
            } else {
                double idade = pet.getIdade();
                if (idade < 1) {
                    int meses = (int) (idade * 12);
                    bw.write("5 - " + meses + " meses");
                } else if (idade % 1 == 0) {
                    bw.write("5 - " + (int) idade + " anos");
                } else {
                    bw.write("5 - " + String.format(Locale.US, "%.1f", idade) + " anos");
                }
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
            bw.newLine();
            bw.write("8 - " + pet.getDateCadastro().format(padraoData));

            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File atualizarNomeFile(Pet pet) {
        String dataFormatada = pet.getDateCadastro().format(padraoData);
        File pasta = new File("petsCadastrados");
        pasta.mkdir();
        File respostaFile;
        if (pet.getNome() == null) {
            String nomePet = Pet.NAO_INFORMADO.replace(" ", "").replace("ã", "a").toUpperCase();
            respostaFile = new File(pasta, dataFormatada.concat("-" + nomePet + ".txt"));
            pet.nomeArquivo = dataFormatada.concat("-" + nomePet + ".txt");
        } else {
            String nomePet = pet.getNome().replace(" ", "").toUpperCase();
            respostaFile = new File(pasta, dataFormatada.concat("-" + nomePet + ".txt"));
            pet.nomeArquivo = dataFormatada.concat("-" + nomePet + ".txt");
        }
        return respostaFile;
    }

    public static void excluirFile(Pet pet) {
        atualizarNomeFile(pet);
        String nomeArquivo = pet.nomeArquivo;
        File pasta = new File("petsCadastrados");
        File file = new File(pasta, nomeArquivo);
        file.delete();
    }
}