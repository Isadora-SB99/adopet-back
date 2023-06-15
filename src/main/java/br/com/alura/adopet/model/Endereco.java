package br.com.alura.adopet.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @NotNull
    @Getter
    @Setter
    @Pattern(regexp = "\\b\\d{5}-?\\d{3}\\b")
    private String cep;

    @NotNull
    @Getter
    @Setter
    private String logradouro;

    @NotNull
    @Getter
    @Setter
    private String bairro;

    @NotNull
    @Getter
    @Setter
    private String cidade;

    @NotNull
    @Getter
    @Setter
    @Size(min = 2, max = 2)
    private String uf;

    @NotNull
    @Getter
    @Setter
    public String numero;

    @Getter
    @Setter
    public String complemento;
}
