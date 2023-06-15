package br.com.alura.adopet.controller;

import br.com.alura.adopet.model.entities.Adocao;
import br.com.alura.adopet.repository.AdocaoRepository;
import br.com.alura.adopet.service.AdocaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adocao")
public class AdocaoController {

    @Autowired
    private AdocaoRepository repository;

    @Autowired
    private AdocaoService service;

    @GetMapping
    public ResponseEntity<Page<Adocao>> listarAdocoes(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "3") int tamanho) {
        Pageable paginacao = PageRequest.of(pagina, tamanho);
        Page<Adocao> adocoes = repository.findAll(paginacao);
        if (adocoes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(adocoes);
        }
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Adocao> criarAdocao(@RequestBody @Valid Adocao adocao, UriComponentsBuilder componentsBuilder) {
        service.adotar(adocao);
        repository.save(adocao);

        var uri = componentsBuilder.path("/adocao/{id}").buildAndExpand(adocao.getId()).toUri();
        return ResponseEntity.created(uri).body(adocao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAdocao(@PathVariable String id) {
        Optional<Adocao> adocao = repository.findById(id);
        if (adocao.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            repository.deleteById(id);
//            return ResponseEntity.ok(adocao);
            return ResponseEntity.ok().build();
        }
    }
}
