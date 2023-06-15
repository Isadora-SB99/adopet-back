package br.com.alura.adopet.repository;

import br.com.alura.adopet.model.entities.Tutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends MongoRepository<Tutor, String> {

}
