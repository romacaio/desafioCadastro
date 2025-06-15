package model.validadores;

import model.exceptions.PesoInvalidoException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RacaValidador {

    public static void validarRaca(String raca) {
        String regex = "^[^\\p{L} ]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(raca);
        boolean isPesoValido = matcher.matches();
        if (!isPesoValido) {
            throw new PesoInvalidoException("Digite um valor válido, Apenas letras são permitidas.");
        }
    }
}
