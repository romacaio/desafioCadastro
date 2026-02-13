package service;

public class PetService {
    private static final String NAO_INFORMADO = "não informado";

    public String analisaIdade(String inputIdade) {
        if (inputIdade.equals("null")) {
            return NAO_INFORMADO;
        }
        Double idade = Double.parseDouble(inputIdade);
        if (idade < 1.0) {
            return idade + " meses";
        }
        return idade + " anos";
    }

    public String analisaNaoInformado(String input) {
        if (input.equals("null")) {
            return NAO_INFORMADO;
        }
        return input;
    }
}
