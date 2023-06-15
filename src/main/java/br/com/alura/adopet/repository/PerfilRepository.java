package br.com.alura.adopet.repository;

import br.com.alura.adopet.model.Perfil;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PerfilRepository extends MongoRepository<Perfil, String> {

    Perfil findByNome(String nome);

}
