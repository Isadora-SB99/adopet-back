package br.com.alura.adopet.service;

import br.com.alura.adopet.model.Usuario;
import br.com.alura.adopet.model.entities.Abrigo;
import br.com.alura.adopet.model.entities.Tutor;
import br.com.alura.adopet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void criaUsuarioTutor(Tutor tutor) {
        Usuario usuarioTutor = new Usuario(tutor.getEmail(), tutor.getSenha());
        usuarioTutor.setPerfis(tutor.getPerfis());
        usuarioRepository.save(usuarioTutor);
    }

    public void criaUsuarioAbrigo(Abrigo abrigo) {
        Usuario usuarioAbrigo = new Usuario(abrigo.getEmail(), abrigo.getSenha());
        usuarioAbrigo.setPerfis(abrigo.getPerfis());
        usuarioRepository.save(usuarioAbrigo);
    }


}
