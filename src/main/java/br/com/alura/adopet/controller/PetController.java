package br.com.alura.adopet.controller;

import br.com.alura.adopet.model.entities.Pet;
import br.com.alura.adopet.repository.PetRepository;
import br.com.alura.adopet.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetRepository repository;

    @Autowired
    private PetService service;


    @GetMapping
    public ResponseEntity<Page<Pet>> listarPets(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "3") int tamanho) {
        Pageable paginacao = PageRequest.of(pagina, tamanho);
        Page<Pet> pets = repository.findByAdotadoFalse(paginacao);
        if (pets.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(pets);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pet>> obterPetPeloId(@PathVariable String id) {
        Optional<Pet> pet = repository.findById(id);
        if (pet.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(pet);
        }
    }

    @GetMapping("/adotados")
    public ResponseEntity<Page<Pet>> listarPetsComAdotados(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "3") int tamanho) {
        Pageable paginacao = PageRequest.of(pagina, tamanho);
        Page<Pet> pets = repository.findAll(paginacao);

        if (pets.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(pets);
        }
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Pet> criarPet(@RequestBody @Valid Pet pet, UriComponentsBuilder componentsBuilder) throws IOException {
        if (pet.getEndereco() == null) {
            service.enderecoPetIgualAbrigo(pet, pet.getAbrigoId());
        }
        boolean urlValidada = service.isImage(pet.getImagem());
        if (!urlValidada) {
            return ResponseEntity.badRequest().build();
        }

        repository.save(pet);
        var uri = componentsBuilder.path("/pets/{id}").buildAndExpand(pet.getId()).toUri();

        return ResponseEntity.created(uri).body(pet);
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, path = "/{id}")
    public ResponseEntity<Pet> alterarPet(@PathVariable String id, @Valid Pet pet) {
        Optional<Pet> optionalPet = repository.findById(id);
        if (optionalPet.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            repository.save(pet);
            return ResponseEntity.ok(pet);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPet(@PathVariable String id) {
        Optional<Pet> pet = repository.findById(id);
        if (pet.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            repository.deleteById(id);
//            return ResponseEntity.ok(pet);
            return ResponseEntity.ok().build();
        }
    }
}
