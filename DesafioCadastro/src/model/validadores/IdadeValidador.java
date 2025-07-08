package model.validadores;

import model.exceptions.IdadeInvalidaException;

public class IdadeValidador {

    public static void validarIdade(Double idade) {
        if (idade > 20 || idade < 0) {
            throw new IdadeInvalidaException("Idade inválida. O valor informado não pode ser negativo e não pode ultrapassar o limite máximo de 20 anos.");
        }
    }
}
