package model.entidades;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class File {
    private static List<String> linhas = new ArrayList<>();

    public static void criarFormulario() {
        java.io.File pasta = new java.io.File("data");
        pasta.mkdir();
        java.io.File formulario = new java.io.File(pasta, "formulario.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(formulario))) {
            bw.write("1 - Qual o nome e sobrenome do pet?\n2 - Qual o tipo do pet (Cachorro/Gato)?\n" +
                    "3 - Qual o sexo do animal?\n4 - Qual endereço e bairro que ele foi encontrado?\n" +
                    "5 - Qual a idade aproximada do pet?\n6 - Qual o peso aproximado do pet?\n" +
                    "7 - Qual a raça do pet?");
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(formulario))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getLinhas() {
        return linhas;
    }
}
