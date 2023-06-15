package br.com.alura.adopet.controller;

import br.com.alura.adopet.model.Usuario;
import br.com.alura.adopet.repository.PerfilRepository;
import br.com.alura.adopet.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(usuarios);

        }
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Usuario> cadastrarUsuario(@RequestBody @Valid Usuario usuario) {
        usuario.addPerfil(perfilRepository.findByNome("ROLE_USER"));
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }


}
