package br.com.alura.adopet.repository;

import br.com.alura.adopet.model.entities.Abrigo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbrigoRepository extends MongoRepository<Abrigo, String> {

}
