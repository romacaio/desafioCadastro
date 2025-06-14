package model.validadores;

import model.exceptions.PetNomeInvalidoException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetValidador {

    public static void validarNome(String nomePet){
        String regex = "^[\\p{L}]+(\\s[\\p{L}]+)+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nomePet);
        boolean isNomeValido = matcher.matches();
        if(isNomeValido == false){
            throw new PetNomeInvalidoException("O nome do pet deve conter nome e sobrenome, apenas letras são permitidas.");
        }
    }
}
