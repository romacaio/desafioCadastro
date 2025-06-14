package model.entidades;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class File {

    public static List<String> criarFormulario() {
        java.io.File pasta = new java.io.File("data");
        pasta.mkdir();
        java.io.File formulario = new java.io.File(pasta, "formulario.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(formulario))) {
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
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(formulario))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas;
    }

}

