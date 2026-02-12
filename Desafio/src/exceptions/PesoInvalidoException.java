package exceptions;

public class PesoInvalidoException extends IllegalArgumentException {

    public PesoInvalidoException() {
        super("Peso inválido!");
    }

    public PesoInvalidoException(String mensagem) {
        super(mensagem);
    }

    public PesoInvalidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
