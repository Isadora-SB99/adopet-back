package br.com.alura.adopet.repository;

import br.com.alura.adopet.model.entities.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends MongoRepository<Pet, String> {

    Page<Pet> findByAdotadoFalse(Pageable pageable);
}
