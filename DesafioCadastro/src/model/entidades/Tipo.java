package model.entidades;

public enum Tipo {
    CACHORRO("Cachorro"),
    GATO("Gato");

    private String nomeRelatorio;

    Tipo(String nomeRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
    }

    public static Tipo tipoPorNomeRelatorio(String tipo_String) throws IllegalArgumentException {
        for (Tipo tipo : values()) {
            if (tipo.getNomeRelatorio().equalsIgnoreCase(tipo_String)) {
                return tipo;
            }
        }
        return null;
    }

    public String getNomeRelatorio() {
        return nomeRelatorio;
    }
}
