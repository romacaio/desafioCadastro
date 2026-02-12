package exceptions;

public class IdadeInvalidaException extends IllegalArgumentException {

    public IdadeInvalidaException() {
        super("Idade inválida!");
    }

    public IdadeInvalidaException(String mensagem) {
        super(mensagem);
    }

    public IdadeInvalidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

