package br.com.alura.adopet.controller;

import br.com.alura.adopet.model.entities.Abrigo;
import br.com.alura.adopet.repository.AbrigoRepository;
import br.com.alura.adopet.repository.PerfilRepository;
import br.com.alura.adopet.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<Abrigo>> listarAbrigos() {
        List<Abrigo> abrigos = abrigoRepository.findAll();
        if (abrigos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(abrigos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Abrigo>> obterAbrigoPeloId(@PathVariable String id) {
        Optional<Abrigo> abrigo = abrigoRepository.findById(id);
        if (abrigo.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(abrigo);
        }
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Abrigo> criarAbrigo(@RequestBody @Valid Abrigo abrigo, UriComponentsBuilder componentsBuilder) {
        abrigo.addPerfil(perfilRepository.findByNome("ROLE_ABRIGO"));
        abrigo.addPerfil(perfilRepository.findByNome("ROLE_USER"));
        abrigo.setSenha(passwordEncoder.encode(abrigo.getSenha()));
        abrigoRepository.save(abrigo);
        usuarioService.criaUsuarioAbrigo(abrigo);

        var uri = componentsBuilder.path("/abrigos/{id}").buildAndExpand(abrigo.getId()).toUri();
        return ResponseEntity.created(uri).body(abrigo);
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, path = "/{id}")
    public ResponseEntity<Abrigo> alterarAbrigo(@PathVariable String id, @Valid Abrigo abrigo) {
        Optional<Abrigo> optionalAbrigo = abrigoRepository.findById(id);
        if (optionalAbrigo.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            abrigoRepository.save(abrigo);
            return ResponseEntity.ok(abrigo);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAbrigo(@PathVariable String id) {
        Optional<Abrigo> abrigo = abrigoRepository.findById(id);
        if (abrigo.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            abrigoRepository.deleteById(id);
//            return ResponseEntity.ok(abrigo);
            return ResponseEntity.ok().build();
        }
    }
}
