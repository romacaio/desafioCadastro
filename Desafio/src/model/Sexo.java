package model;

import exceptions.SexoParseException;

public enum Sexo {
    MACHO("Macho"),
    FEMEA("Femea");

    private final String nomeUsual;

    private Sexo(String nomeUsual) {
        this.nomeUsual = nomeUsual;
    }

    public Sexo parse(String sexoString) {
        for (Sexo sexo : Sexo.values()) {
            if (sexoString.equalsIgnoreCase(sexo.nomeUsual)) {
                return sexo;
            }
        }
        throw new SexoParseException();
    }

    public String getNomeUsual() {
        return nomeUsual;
    }
}
