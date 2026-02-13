package service;

import exceptions.*;

public class PetValidator {

    public String validaNome(String nome) {
        if (nome.isBlank()) {
            return null;
        }
        if (!nome.matches("^\\s+$|(?<primeiroNome>[a-zA-Z]+)(\\s[a-zA-Z]+)+$")) {
            throw new NomeInvalidoException();
        }
        return nome;
    }

    public Double validaIdade(String idadeString) {
        if (idadeString.isBlank()) {
            return null;
        }

        Double idade = Double.parseDouble(idadeString.replaceAll(",", "."));
        if (idade > 20.0 || idade < 0.0) {
            throw new IdadeInvalidaException();
        }
        return idade;
    }

    public Double validaPeso(String pesoString) {
        if (pesoString.isBlank()) {
            return null;
        }

        Double peso = Double.parseDouble(pesoString.replaceAll(",", "."));
        if (peso > 60 || peso < 0.5) {
            throw new PesoInvalidoException();
        }
        return peso;
    }

    public Integer ValidaNumero(String numeroString) {
        if (numeroString.isBlank()) {
            return null;
        }

        Integer numero = Integer.parseInt(numeroString);
        if (numero < 0) {
            throw new EnderecoInvalidoException("Número de endereco Inválido!");
        }
        return numero;
    }

    public String validaRua(String rua) {
        if (!rua.matches("^[a-zA-Z\\s]+$")) {
            throw new EnderecoInvalidoException("Nome de Rua inválida!");
        }
        return rua;
    }

    public String validaCidade(String cidade) {
        if (!cidade.matches("^[a-zA-Z\\s]+$")) {
            throw new EnderecoInvalidoException("Nome de cidade Inválida!");
        }
        return cidade;
    }

    public String validaRaca(String raca) {
        if (!raca.matches("^\\s+$|(^[a-zA-Z]+)(\\s[a-zA-Z]+)*$")) {
            throw new RacaInvalidaException();
        }
        return raca;
    }

}