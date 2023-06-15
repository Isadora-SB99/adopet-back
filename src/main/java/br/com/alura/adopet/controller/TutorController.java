package br.com.alura.adopet.controller;

import br.com.alura.adopet.model.entities.Tutor;
import br.com.alura.adopet.repository.PerfilRepository;
import br.com.alura.adopet.repository.TutorRepository;
import br.com.alura.adopet.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public ResponseEntity<List<Tutor>> listarTutores() {
        List<Tutor> tutores = tutorRepository.findAll();
        if (tutores.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tutores);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Tutor>> obterTutorPeloId(@PathVariable String id) {
        Optional<Tutor> tutor = tutorRepository.findById(id);
        if (tutor.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tutor);
        }

    }

    @PostMapping
    public @ResponseBody ResponseEntity<Tutor> criarTutor(@RequestBody @Valid Tutor tutor, UriComponentsBuilder componentsBuilder) {
        tutor.addPerfil(perfilRepository.findByNome("ROLE_TUTOR"));
        tutor.addPerfil(perfilRepository.findByNome("ROLE_USER"));
        tutor.setSenha(passwordEncoder.encode(tutor.getSenha()));
        tutorRepository.save(tutor);
        usuarioService.criaUsuarioTutor(tutor);

        var uri = componentsBuilder.path("/tutores/{id}").buildAndExpand(tutor.getId()).toUri();

        return ResponseEntity.created(uri).body(tutor);
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{id}")
    public ResponseEntity<Tutor> alterarTutor(@PathVariable String id, @RequestBody @Valid Tutor tutor) {
        Optional<Tutor> optionalTutor = tutorRepository.findById(id);
        if (optionalTutor.isEmpty()) {

            return ResponseEntity.notFound().build();
        } else {
            tutorRepository.save(tutor);
            return ResponseEntity.ok(tutor);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTutor(@PathVariable String id) {
        Optional<Tutor> tutor = tutorRepository.findById(id);
        if (tutor.isEmpty()) {

            return ResponseEntity.notFound().build();
        } else {
            tutorRepository.deleteById(id);
//            return ResponseEntity.ok(tutor);
            return ResponseEntity.ok().build();
        }
    }
}
