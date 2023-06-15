package br.com.alura.adopet.model.entities;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "adocao")
@AllArgsConstructor
@NoArgsConstructor
public class Adocao {

    @Id
    @Getter
    private String id;

    @NotNull
    @Getter
    @Setter
    private String petId;

    @NotNull
    @Getter
    @Setter
    private String tutorId;

    @NotNull
    @Getter
    @Setter
    private LocalDate data;


}