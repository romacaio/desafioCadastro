package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FormularioRepository {
    private static File file = new File("src\\repository\\formulario.txt");

    public void criarFormulario() {
        if (!file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                file.createNewFile();

                bw.write("1 - Qual o nome e sobrenome do pet?");
                bw.newLine();
                bw.write("2 - Qual o tipo do pet (Cachorro/Gato)?");
                bw.newLine();
                bw.write("3 - Qual o sexo do animal?");
                bw.newLine();
                bw.write("4 - Qual endereço e bairro que ele foi encontrado?");
                bw.newLine();
                bw.write("5 - Qual a idade aproximada do pet?");
                bw.newLine();
                bw.write("6 - Qual o peso aproximado do pet?");
                bw.newLine();
                bw.write("7 - Qual a raça do pet?");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> lerFormulario() {
        List<String> perguntas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                perguntas.add(linha);
            }
            return perguntas;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
