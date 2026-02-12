package model;

import exceptions.TipoParseException;

public enum Tipo {
    CACHORRO("Cachorro"),
    GATO("Gato");

    private final String nomeUsual;

    private Tipo(String nomeUsual) {
        this.nomeUsual = nomeUsual;
    }

    public static Tipo parse(String tipoString) {
        for (Tipo tipo : Tipo.values()) {
            if (tipoString.equalsIgnoreCase(tipo.nomeUsual)) {
                return tipo;
            }
        }
        throw new TipoParseException();
    }

    public String getNomeUsual() {
        return nomeUsual;
    }
}
