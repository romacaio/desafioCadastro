package exceptions;

import java.io.FileNotFoundException;

public class PetNotFoundException extends FileNotFoundException {

    public PetNotFoundException() {
        super("Pet buscado não econtrado!");
    }

    public PetNotFoundException(String mensagem) {
        super(mensagem);
    }
}
