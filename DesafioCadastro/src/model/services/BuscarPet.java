package model.services;

import model.entidades.Pet;
import model.entidades.Sexo;
import model.entidades.Tipo;
import model.util.TextoUtil;

import java.util.ArrayList;
import java.util.List;

public class BuscarPet {

    public static List<Pet> porTipo(Tipo tipo) {
        List<Pet> listaFiltradaPorTipo = new ArrayList<>();
        for (Pet pet : CadastrarPet.getPetsCadastrados()) {
            if (pet.getTipo() == tipo) {
                listaFiltradaPorTipo.add(pet);
            }
        }
        if (listaFiltradaPorTipo.isEmpty()) {
            return null;
        } else {
            return listaFiltradaPorTipo;
        }
    }

    public static List<Pet> porNome(Tipo tipo, String nome) {
        String nomeNormalizado = TextoUtil.normalizar(nome); // remover os acentos
        List<Pet> listaFiltradaPorTipo = porTipo(tipo);
        List<Pet> resultado = new ArrayList<>();

        for (Pet pet : listaFiltradaPorTipo) {
            String nomePetNormalizado = TextoUtil.normalizar(pet.getNome());
            if (nomePetNormalizado.contains(nomeNormalizado)) {
                resultado.add(pet);
            }
        }
        if (resultado.isEmpty()) {
            return null;
        } else {
            return resultado;
        }
    }

    public static List<Pet> porSexo(Tipo tipo, String sexo) {
        List<Pet> listaFiltradaPorTipo = porTipo(tipo);
        List<Pet> resultado = new ArrayList<>();
        for (Pet pet : listaFiltradaPorTipo) {
            if (pet.getSexo().getNomeRelatorio().equals(sexo)) {
                resultado.add(pet);
            }
        }
        return resultado;
    }

    public static List<Pet> porIdade(Tipo tipo, double idade) {
        List<Pet> listaFiltradaPorTipo = porTipo(tipo);
        List<Pet> resultado = new ArrayList<>();
        for (Pet pet : listaFiltradaPorTipo) {
            if (pet.getIdade() == idade) {
                resultado.add(pet);
            }
        }
        if (resultado.isEmpty()) {
            return null;
        } else {
            return resultado;
        }
    }

    public static List<Pet> porRaca(Tipo tipo, String raca) {
        String racaNormalizada = TextoUtil.normalizar(raca);
        List<Pet> listaFiltradaPorTipo = porTipo(tipo);
        List<Pet> resultado = new ArrayList<>();
        for (Pet pet : listaFiltradaPorTipo) {
            String racaPetNormalizada = TextoUtil.normalizar(pet.getRaca());
            if (racaPetNormalizada.equals(racaNormalizada)) {
                resultado.add(pet);
            }
        }
        return resultado;
    }

}
