package model.exceptions;

public class PetNomeInvalidoException extends RuntimeException {
    public PetNomeInvalidoException(String mensagem) {
        super(mensagem);
    }
}
