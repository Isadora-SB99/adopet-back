package br.com.alura.adopet.model.entities;

import br.com.alura.adopet.model.Usuario;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tutores")
@AllArgsConstructor
@NoArgsConstructor
public class Tutor extends Usuario {

    @NotNull
    @Getter
    @Setter
    private String nome;

}
