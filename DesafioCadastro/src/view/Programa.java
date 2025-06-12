package view;

import model.entidades.File;

public class Programa {
    public static void main(String[] args) {
        File.criarFormulario();
        for(String pergunta : File.getLinhas()){
            System.out.println(pergunta);
        }
    }
}
