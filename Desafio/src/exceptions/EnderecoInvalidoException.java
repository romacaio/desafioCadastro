package exceptions;

public class EnderecoInvalidoException extends IllegalArgumentException {

    public EnderecoInvalidoException() {
        super("Endereço inválido!");
    }

    public EnderecoInvalidoException(String mensagem) {
        super(mensagem);
    }

    public EnderecoInvalidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
