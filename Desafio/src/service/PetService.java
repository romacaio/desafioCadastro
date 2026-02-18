package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetService {
    public static final String NAO_INFORMADO = "nao informado";

    public String analisaNaoInformado(String input) {
        String inputNormalizado = input.trim();

        if (inputNormalizado.equals("null")) {
            return NAO_INFORMADO;
        }
        if (inputNormalizado.equals(NAO_INFORMADO)) {
            return null;
        }
        return inputNormalizado;
    }

    public String extraiDadoFile(String linhaFile) {

        Pattern regex = Pattern.compile("([a-zA-Z]+)([,\\w\\d\\s]+)$");
        Matcher matcher = regex.matcher(linhaFile);

        if (linhaFile.contains("anos") || linhaFile.contains("meses") || linhaFile.contains("Kg")) {
            regex = Pattern.compile("\\d\\.\\d");
            matcher = regex.matcher(linhaFile);
            matcher.find();
            return matcher.group();

        } else if (matcher.find()) {
            return matcher.group();

        } else {
            return NAO_INFORMADO;
        }
    }

}
