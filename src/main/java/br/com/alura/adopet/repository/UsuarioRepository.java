package br.com.alura.adopet.repository;

import br.com.alura.adopet.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Usuario findByEmail(String email);
}
