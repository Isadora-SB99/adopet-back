package br.com.alura.adopet.service;

import br.com.alura.adopet.model.entities.Abrigo;
import br.com.alura.adopet.model.entities.Pet;
import br.com.alura.adopet.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private AbrigoRepository abrigoRepository;

    public void enderecoPetIgualAbrigo(Pet pet, String abrigoId) {
        Optional<Abrigo> abrigo = abrigoRepository.findById(abrigoId);
        pet.setEndereco(abrigo.get().getEndereco());
    }

    public boolean isImage(String url) throws IOException {
        Image image = ImageIO.read(new URL(url));
        if (image != null) {
            System.out.println("IMAGE");
            return true;
        } else {
            System.out.println("NOT IMAGE");
            return false;
        }
    }
}
