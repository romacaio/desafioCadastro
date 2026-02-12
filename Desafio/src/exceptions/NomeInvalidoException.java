package exceptions;

public class NomeInvalidoException extends IllegalArgumentException {

    public NomeInvalidoException() {
        super("Nome inválido!");
    }

    public NomeInvalidoException(String mensagem) {
        super(mensagem);
    }

    public NomeInvalidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

