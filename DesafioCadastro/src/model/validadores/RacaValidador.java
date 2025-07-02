package model.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RacaValidador {

    public static void validarRaca(String raca) {
        String regex = "^[\\p{L}\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(raca);
        boolean isRacaValida = matcher.matches();
        if (!isRacaValida) {
            throw new IllegalArgumentException("Digite um valor válido, apenas letras são permitidas.");
        }
    }
}
