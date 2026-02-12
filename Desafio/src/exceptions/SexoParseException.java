package exceptions;

public class SexoParseException extends IllegalArgumentException {

    public SexoParseException() {
        super("Sexo inexistente!");
    }

    public SexoParseException(String mensagem) {
        super(mensagem);
    }

    public SexoParseException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
