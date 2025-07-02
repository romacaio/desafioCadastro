package model.entidades;

import model.util.TextoUtil;

public enum Sexo {
    MACHO("Macho"),
    FEMEA("Fêmea");

    private String nomeRelatorio;

    Sexo(String nomeRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
    }

    public static Sexo sexoPorNomeRelatorio(String sexo_String) throws IllegalArgumentException {
        for (Sexo sexo : values()) {
            if (sexo.getNomeRelatorio().equalsIgnoreCase(sexo_String)) {
                return sexo;
            }
        }
        return null;
    }

    public String getNomeRelatorio() {
        return nomeRelatorio;
    }
}
