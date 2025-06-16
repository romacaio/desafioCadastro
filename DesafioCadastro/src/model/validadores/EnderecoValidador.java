package model.validadores;

import model.exceptions.EnderecoInvalidoException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnderecoValidador {

    public static void validarEndereco(String endereco) {
        String regex = "^[\\p{L}\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(endereco);
        boolean isEnderecoValido = matcher.matches();
        if (!isEnderecoValido) {
            throw new EnderecoInvalidoException("Digite um valor válido, apenas letras são permitidas.");
        }
    }
}
