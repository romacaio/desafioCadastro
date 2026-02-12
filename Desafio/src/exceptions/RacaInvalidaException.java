package exceptions;

public class RacaInvalidaException extends IllegalArgumentException {

    public RacaInvalidaException() {
        super("Nome de raça inválida!");
    }

    public RacaInvalidaException(String mensagem) {
        super(mensagem);
    }

    public RacaInvalidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
