package br.com.alura.adopet.repository;

import br.com.alura.adopet.model.entities.Adocao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdocaoRepository extends MongoRepository<Adocao, String> {
}
