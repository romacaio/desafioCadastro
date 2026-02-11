package main;

import repository.FormularioRepository;

public class Aplicacao {
    public static void main(String[] args) {
        FormularioRepository formulario = new FormularioRepository();
        formulario.criarFormulario();
    }
}
