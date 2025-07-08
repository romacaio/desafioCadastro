package model.util;

import java.text.Normalizer;

public class TextoUtil {

    public static String normalizar(String texto) {
        if (texto == null) {
            return "";
        }
        return texto.toLowerCase()
                .replace("á", "a")
                .replace("à", "a")
                .replace("ã", "a")
                .replace("â", "a")
                .replace("é", "e")
                .replace("ê", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("õ", "o")
                .replace("ô", "o")
                .replace("ú", "u")
                .replace("ç", "c");
    }
}
