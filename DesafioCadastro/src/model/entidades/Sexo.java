package model.entidades;

public enum Sexo {
    MASCULIMO("Masculino"),
    FEMININO("Feminino");

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
