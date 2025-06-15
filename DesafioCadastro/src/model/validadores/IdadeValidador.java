package model.validadores;

import model.exceptions.IdadeInvalidaException;

public class IdadeValidador {

    public static void validarIdade(Double idade) {
        if (idade > 20) {
            throw new IdadeInvalidaException("Idade inválida. O valor informado ultrapassa o limite máximo de 20 anos.");
        }

    }
}
