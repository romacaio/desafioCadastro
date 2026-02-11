package model;

public class Pet {
    private String nome;
    private Tipo tipo;
    private Sexo sexo;
    private Endereco endereco;
    private Double idade;
    private Double peso;
    private String raca;

    public Pet() {

    }

    public Pet(String nome, Tipo tipo, Sexo sexo, Endereco endereco, Double idade, Double peso, String raca) {
        this.nome = nome;
        this.tipo = tipo;
        this.sexo = sexo;
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Double getIdade() {
        return idade;
    }

    public void setIdade(Double idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nome).append(" - ").append(sexo).append(" - ").append(endereco).append(" - ")
                .append(idade).append(" anos").append(" - ").append(peso).append("Kg")
                .append(" - ").append(raca);

        return sb.toString();
    }
}
