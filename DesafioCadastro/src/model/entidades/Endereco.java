package model.entidades;

public class Endereco {
    private Integer numeroCasa;
    private String cidade;
    private String rua;

    public Endereco() {

    }

    public Endereco(Integer numeroCasa, String cidade, String rua) {
        this.numeroCasa = numeroCasa;
        this.cidade = cidade;
        this.rua = rua;
    }

    public Integer getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(Integer numeroCasa) {
        this.numeroCasa = numeroCasa;
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
}
