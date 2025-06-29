package model.entidades;

import model.validadores.NomeValidador;

import java.util.Locale;

public class Pet {
    public static final String NAO_INFORMADO = "Não informado";
    private String nome;
    private Tipo tipo;
    private Sexo sexo;
    private Endereco endereco;
    private Double idade;
    private Double peso;
    private String raca;
    public String nomeArquivo;

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

    public static boolean isVazio(String resposta) {
        if (resposta.trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public String toString() {

        String nomeStr = (nome != null) ? nome : NAO_INFORMADO;
        String pesoStr = (peso != null) ? String.format(Locale.US, "%.1f", peso) : NAO_INFORMADO;
        String racaStr = (raca != null) ? raca : NAO_INFORMADO;
        String numeroCasaStr = (getEndereco().getNumeroCasa() != null) ? getEndereco().getNumeroCasa().toString() : NAO_INFORMADO;
        String cidadeStr = endereco.getCidade();
        String ruaStr = endereco.getRua();

        String idadeStr;
        if (idade != null) {
            if (idade < 1) {
                int meses = (int) (idade * 12);
                idadeStr = meses + " meses";
            } else {
                idadeStr = String.format(Locale.US, "%.1f anos", idade);
            }
        } else {
            idadeStr = NAO_INFORMADO;
        }

        return String.format("%s - %s - %s - Rua %s, %s - Cidade %s - %s - %s - %s", nomeStr, tipo.getNomeRelatorio(), sexo.getNomeRelatorio(),
                ruaStr, numeroCasaStr, cidadeStr, idadeStr, pesoStr, racaStr);
    }
}
