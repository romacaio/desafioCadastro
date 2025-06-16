package model.validadores;

import model.exceptions.PesoInvalidoException;

public class PesoValidador {
    public static void validarPeso(Double peso) {
        if (peso > 60 || peso < 0.5) {
            throw new PesoInvalidoException("Peso inválido. o peso deve estar entre 0.5kg e 60kg.");
        }
    }
}
