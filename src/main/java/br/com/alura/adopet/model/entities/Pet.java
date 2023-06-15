package br.com.alura.adopet.model.entities;

import br.com.alura.adopet.model.Endereco;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pets")
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    @Id
    @Getter
    private String id;

    @NotNull
    @Getter
    @Setter
    private String abrigoId;

    @NotNull
    @Getter
    @Setter
    private String nome;

    @NotNull
    @Getter
    @Setter
    private String descricao;

    @NotNull
    @Getter
    @Setter
    private boolean adotado = false;

    @NotNull
    @Getter
    @Setter
    private String idade;

    @Getter
    @Setter
    private Endereco endereco;

    @NotNull
    @Getter
    @Setter
    @URL()
    private String imagem;

//    public Pet() {
//    }
//
//    public Pet(String id, String abrigoId, String nome, String descricao, boolean adotado, String idade, Endereco endereco, String imagem) {
//        this.id = id;
//        this.abrigoId = abrigoId;
//        this.nome = nome;
//        this.descricao = descricao;
//        this.adotado = adotado;
//        this.idade = idade;
//        this.endereco = endereco;
//        this.imagem = imagem;
//    }
}
