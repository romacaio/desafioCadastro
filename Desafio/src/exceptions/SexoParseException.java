package exceptions;

public class SexoParseException extends IllegalArgumentException {

    public SexoParseException() {
        System.out.println("Sexo inexistente");
    }

    public SexoParseException(String mensagem) {
        super(mensagem);
    }

    public SexoParseException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
