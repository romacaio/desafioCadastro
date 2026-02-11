package exceptions;

public class TipoParseException extends IllegalArgumentException {

    public TipoParseException() {
        System.out.println("Tipo inexistente");
    }

    public TipoParseException(String mensagem) {
        super(mensagem);
    }

    public TipoParseException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

