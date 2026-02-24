package service;

import model.CriterioBusca;
import model.Pet;
import model.Sexo;
import model.Tipo;
import repository.PetRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class PetService {
    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public static String analisaNaoInformado(String input) {
        String inputNormalizado = input.trim();

        if (inputNormalizado.equals("null")) {
            return Pet.NAO_INFORMADO;
        }
        if (inputNormalizado.equals(Pet.NAO_INFORMADO)) {
            return null;
        }
        return inputNormalizado;
    }

    public List<Pet> buscaPet(CriterioBusca criterioBusca) throws FileNotFoundException {

        List<Pet> petsCadastrados = petRepository.carregarPetsFile();
        filtrarPorTipo(petsCadastrados, criterioBusca.getTipoPet());

        if (criterioBusca.getPeso() != null) filtrarPorPeso(petsCadastrados, criterioBusca.getPeso());
        if (criterioBusca.getSexo() != null) filtrarPorSexo(petsCadastrados, criterioBusca.getSexo());
        if (criterioBusca.getIdade() != null) filtrarPorIdade(petsCadastrados, criterioBusca.getIdade());
        if (criterioBusca.getPeso() != null) filtrarPorPeso(petsCadastrados, criterioBusca.getPeso());
        if (criterioBusca.getRaca() != null) filtrarPorRaca(petsCadastrados, criterioBusca.getRaca());
        if (criterioBusca.getEndereco() != null) filtrarPorEndereco(petsCadastrados, criterioBusca.getEndereco());

        return petsCadastrados;
    }

    public void filtrarPorTipo(List<Pet> petsCadastrados, Tipo tipoBusca) {
        List<Pet> petsRemove = new ArrayList<>();
        for (Pet pet : petsCadastrados) {
            if (!pet.getTipo().equals(tipoBusca)) {
                petsRemove.add(pet);
            }
        }
        removerPets(petsRemove, petsCadastrados);
    }

    public void filtrarPorSexo(List<Pet> petsCadastrados, Sexo sexoBusca) {
        List<Pet> petsRemove = new ArrayList<>();
        for (Pet pet : petsCadastrados) {
            if (!pet.getSexo().equals(sexoBusca)) {
                petsRemove.add(pet);
            }
        }
        removerPets(petsRemove, petsCadastrados);
    }

    public void filtrarPorIdade(List<Pet> petsCadastrados, Double idadeBusca) {
        String idadeBuscaAnalizado = analisaNaoInformado(String.valueOf(idadeBusca));
        String idade;
        List<Pet> petsRemove = new ArrayList<>();

        for (Pet pet : petsCadastrados) {
            idade = analisaNaoInformado(String.valueOf(idadeBuscaAnalizado));
            if (idade.equalsIgnoreCase(idadeBuscaAnalizado)) {
                petsRemove.add(pet);
            }
        }
        removerPets(petsRemove, petsCadastrados);
    }

    public void filtrarPorPeso(List<Pet> petsCadastrados, Double pesoBusca) {
        String pesoBuscaAnalizado = analisaNaoInformado(String.valueOf(pesoBusca));
        String peso;
        List<Pet> petsRemove = new ArrayList<>();

        for (Pet pet : petsCadastrados) {
            peso = analisaNaoInformado(String.valueOf(pet.getPeso()));
            if (!peso.equalsIgnoreCase(pesoBuscaAnalizado)) {
                petsRemove.add(pet);
            }
        }
        removerPets(petsRemove, petsCadastrados);
    }

    public void filtrarPorEndereco(List<Pet> petsCadastrados, String enderecoBusca) {
        String enderecoBuscaAnalizado = analisaNaoInformado(String.valueOf(enderecoBusca).toLowerCase());
        String endereco;
        List<Pet> petsRemove = new ArrayList<>();

        for (Pet pet : petsCadastrados) {
            endereco = pet.getEndereco().toString().toLowerCase().toLowerCase();
            if (!endereco.contains(enderecoBuscaAnalizado)) {
                petsRemove.add(pet);
            }
        }
        removerPets(petsRemove, petsCadastrados);
    }

    public void filtrarPorRaca(List<Pet> petsCadastrados, String racaBusca) {
        String racaBuscaAnalizado = analisaNaoInformado(String.valueOf(racaBusca).toLowerCase());
        String raca;
        List<Pet> petsRemove = new ArrayList<>();

        for (Pet pet : petsCadastrados) {
            raca = analisaNaoInformado(String.valueOf(pet.getRaca()).toLowerCase());
            if (!raca.contains(racaBuscaAnalizado)) {
                petsRemove.add(pet);
            }
        }
        removerPets(petsRemove, petsCadastrados);
    }

    public void filtrarPorNome(List<Pet> petsCadastrados, String nomeBusca) {
        String nomeBuscaAnalizado = analisaNaoInformado(String.valueOf(nomeBusca)).toLowerCase();
        String nome;
        List<Pet> petsRemove = new ArrayList<>();

        for (Pet pet : petsCadastrados) {
            nome = analisaNaoInformado(String.valueOf(pet.getNome()).toLowerCase());
            if (!nome.contains(nomeBuscaAnalizado)) {
                petsRemove.add(pet);
            }
        }
        removerPets(petsRemove, petsCadastrados);
    }

    public void removerPets(List<Pet> petsRemove, List<Pet> petsCadastrados) {
        for (Pet pet : petsRemove) {
            petsCadastrados.remove(pet);
        }
    }
}

