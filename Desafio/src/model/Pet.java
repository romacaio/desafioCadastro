package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Pet {
    public static final String NAO_INFORMADO = "nao informado";

    private String nome;
    private Tipo tipo;
    private Sexo sexo;
    private Endereco endereco;
    private Double idade;
    private Double peso;
    private String raca;
    private LocalDateTime dateCadastro;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Pet pet = (Pet) o;
        return Objects.equals(this.nome, pet.getNome()) &&
                Objects.equals(this.tipo, pet.getTipo()) &&
                Objects.equals(this.sexo, pet.getSexo()) &&
                Objects.equals(this.raca, pet.getRaca()) &&
                Objects.equals(this.idade, pet.getIdade()) &&
                Objects.equals(this.peso, pet.getPeso());
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

    public LocalDateTime getDateCadastro() {
        return dateCadastro;
    }

    public void setDateCadastro(LocalDateTime dateCadastro) {
        this.dateCadastro = dateCadastro;
    }

    public String getIdadeFormatada() {
        if (idade == null) {
            return NAO_INFORMADO;
        }
        if (idade > 1.0) {
            return idade + " anos";
        }

        return idade + " meses";
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nome != null ? nome : NAO_INFORMADO).append(" - ").append(tipo.getNomeUsual()).append(" - ")
                .append(sexo.getNomeUsual()).append(" - ").append(endereco).append(" - ")
                .append(getIdadeFormatada()).append(" - ").append(peso != null ? peso + "Kg" : NAO_INFORMADO)
                .append(" - ").append(raca != null ? raca : NAO_INFORMADO);

        return sb.toString();
    }
}
