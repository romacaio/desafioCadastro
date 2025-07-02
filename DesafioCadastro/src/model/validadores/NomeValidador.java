package model.validadores;

import model.exceptions.PetNomeInvalidoException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NomeValidador {

    public static void validarNomeCadastro(String nomePet) {
        String regex = "^[\\p{L}]+(\\s[\\p{L}]+)+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nomePet);
        boolean isNomeValido = matcher.matches();
        if (!isNomeValido) {
            throw new PetNomeInvalidoException("O nome do pet deve conter nome e sobrenome, apenas letras são permitidas.");
        }
    }

    public static void validarNomeBusca(String nomeBusca) {
        String regex = "^[^\\d]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nomeBusca);
        boolean isNomeValido = matcher.matches();
        if (!isNomeValido) {
            throw new PetNomeInvalidoException("O nome do pet deve conter nome e sobrenome, apenas letras são permitidas.");
        }
    }
}
