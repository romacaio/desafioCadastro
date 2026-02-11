package main;

import repository.FormularioRepository;

import java.util.List;

public class Aplicacao {
    public static void main(String[] args) {
        FormularioRepository formulario = new FormularioRepository();
        formulario.criarFormulario();

        List<String> perguntas = formulario.lerFormulario();

        for (String petgunta : perguntas) {
            System.out.println(petgunta);
        }

    }
}
