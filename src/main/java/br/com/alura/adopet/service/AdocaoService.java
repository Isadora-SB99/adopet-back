package br.com.alura.adopet.service;

import br.com.alura.adopet.model.entities.Adocao;
import br.com.alura.adopet.model.entities.Pet;
import br.com.alura.adopet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdocaoService {

    @Autowired
    private PetRepository petRepository;

    public void adotar(Adocao adocao) {
        Optional<Pet> petOptional = petRepository.findById(adocao.getPetId());

        if (petOptional.isPresent()) {
            Pet pet = new Pet(
                    petOptional.get().getId(),
                    petOptional.get().getAbrigoId(),
                    petOptional.get().getNome(),
                    petOptional.get().getDescricao(),
                    true,
                    petOptional.get().getIdade(),
                    petOptional.get().getEndereco(),
                    petOptional.get().getImagem());
            petRepository.save(pet);
        }
    }
}
