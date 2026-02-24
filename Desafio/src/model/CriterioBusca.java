package model;

public class CriterioBusca {
    private Tipo tipoPet;
    private String nomeOuSobrenome;
    private Sexo sexo;
    private String peso;
    private String idade;
    private String raca;
    private String endereco;

    public CriterioBusca() {

    }

    public Tipo getTipoPet() {
        return tipoPet;
    }

    public void setTipoPet(Tipo tipoPet) {
        this.tipoPet = tipoPet;
    }

    public String getNomeOuSobrenome() {
        return nomeOuSobrenome;
    }

    public void setNomeOuSobrenome(String nomeOuSobrenome) {
        this.nomeOuSobrenome = nomeOuSobrenome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String  getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
