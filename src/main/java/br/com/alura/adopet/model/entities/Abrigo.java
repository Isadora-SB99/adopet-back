package br.com.alura.adopet.model.entities;

import br.com.alura.adopet.model.Endereco;
import br.com.alura.adopet.model.Usuario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "abrigos")
@AllArgsConstructor
@NoArgsConstructor
public class Abrigo extends Usuario {

    @NotNull
    @Getter
    @Setter
    private String nome;

    @NotNull
    @Getter
    @Setter
    @Pattern(regexp = "\\b\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}\\b")
    private String telefone;

    @NotNull
    @Getter
    @Setter
    private Endereco endereco;

}
