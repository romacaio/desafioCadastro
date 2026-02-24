package model;

import service.PetService;

public class Endereco {
    private Integer numero;
    private String cidade;
    private String rua;

    public Endereco() {

    }

    public Endereco(Integer numero, String ciadade, String rua) {
        this.numero = numero;
        this.cidade = ciadade;
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rua ").append(rua).append(", ").append(numero != null ? numero : Pet.NAO_INFORMADO).append(", ").append(cidade);
        return sb.toString();
    }
}
