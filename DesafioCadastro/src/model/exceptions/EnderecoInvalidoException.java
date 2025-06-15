package model.exceptions;

public class EnderecoInvalidoException extends RuntimeException {
    public EnderecoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
