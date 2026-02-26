package service;

import exceptions.PetNotFoundException;
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
        if (input.equals("null")) {
            return Pet.NAO_INFORMADO;
        }

        String inputNormalizado = input.trim();
        if (inputNormalizado.equals(Pet.NAO_INFORMADO)) {
            return null;
        }
        return inputNormalizado;
    }

    public List<Pet> buscaPet(CriterioBusca criterioBusca) throws FileNotFoundException {
        List<Pet> petsCadastrados = petRepository.carregarPetsFile();
        filtrarPorTipo(petsCadastrados, criterioBusca.getTipoPet());

        if (criterioBusca.getNomeOuSobrenome() != null)
            filtrarPorNome(petsCadastrados, criterioBusca.getNomeOuSobrenome());
        if (criterioBusca.getPeso() != null) filtrarPorPeso(petsCadastrados, criterioBusca.getPeso());
        if (criterioBusca.getSexo() != null) filtrarPorSexo(petsCadastrados, criterioBusca.getSexo());
        if (criterioBusca.getIdade() != null) filtrarPorIdade(petsCadastrados, criterioBusca.getIdade());
        if (criterioBusca.getPeso() != null) filtrarPorPeso(petsCadastrados, criterioBusca.getPeso());
        if (criterioBusca.getRaca() != null) filtrarPorRaca(petsCadastrados, criterioBusca.getRaca());
        if (criterioBusca.getEndereco() != null) filtrarPorEndereco(petsCadastrados, criterioBusca.getEndereco());

        if (petsCadastrados.isEmpty()) {
            throw new PetNotFoundException();
        }
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

    public void filtrarPorIdade(List<Pet> petsCadastrados, String idadeBusca) {
        String idade;
        List<Pet> petsRemove = new ArrayList<>();

        for (Pet pet : petsCadastrados) {
            idade = analisaNaoInformado(String.valueOf(pet.getIdade()));
            if (!idade.equalsIgnoreCase(idadeBusca)) {
                petsRemove.add(pet);
            }
        }
        removerPets(petsRemove, petsCadastrados);
    }

    public void filtrarPorPeso(List<Pet> petsCadastrados, String pesoBusca) {
        String peso;
        List<Pet> petsRemove = new ArrayList<>();

        for (Pet pet : petsCadastrados) {
            peso = analisaNaoInformado(String.valueOf(pet.getPeso()));
            if (!peso.equalsIgnoreCase(pesoBusca)) {
                petsRemove.add(pet);
            }
        }
        removerPets(petsRemove, petsCadastrados);
    }

    public void filtrarPorEndereco(List<Pet> petsCadastrados, String enderecoBusca) {
        String enderecoBuscaAnalizado = enderecoBusca.toLowerCase();
        String endereco;
        List<Pet> petsRemove = new ArrayList<>();

        for (Pet pet : petsCadastrados) {
            endereco = pet.getEndereco().toString().toLowerCase().replace("rua", "");
            if (!endereco.contains(enderecoBuscaAnalizado)) {
                petsRemove.add(pet);
            }
        }
        removerPets(petsRemove, petsCadastrados);
    }

    public void filtrarPorRaca(List<Pet> petsCadastrados, String racaBusca) {
        String racaBuscaAnalizado = racaBusca.toLowerCase();
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
        String nomeBuscaAnalizado = nomeBusca.toLowerCase();
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