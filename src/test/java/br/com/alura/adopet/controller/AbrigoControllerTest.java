package br.com.alura.adopet.controller;

import br.com.alura.adopet.model.Endereco;
import br.com.alura.adopet.model.entities.Abrigo;
import br.com.alura.adopet.repository.AbrigoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
@ActiveProfiles("test")
public class AbrigoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AbrigoRepository mockRepository;

    @Test
    public void deveriaRetornar404NaoExiste() throws Exception {
        URI uri = new URI("/abrigos");
        String json = "{\"email\":\"admin\", \"senha\":\"admin\"}";
        Abrigo abrigo = new Abrigo("nome", "51984628005", new Endereco("95600000", "rua tal","bairro","taqaura","rs", "1815","complemento"));

        when(mockRepository.findById("")).thenReturn(Optional.of(abrigo));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri)
                        .header("authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgQWRvcGV0Iiwic3ViIjoiYWRtaW4iLCJleHAiOjE2ODY4MzkzMjF9.pLynyH-V-L0j8s68UCU9TzBvCwDG4VhHkzb3GZis3iI")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().is(404));
    }

    @Test
    public void deveriaRetornar200AbrigoExiste() throws Exception {
        URI uri = new URI("/abrigos");
        String json = "{\"email\":\"admin\", \"senha\":\"admin\"}";
        Abrigo abrigo = new Abrigo("nova ong", "51 984628005", new Endereco("95600000", "Rua Júlio de Castilhos","Centro","Taquara","RS", "2950","predio verde"));

        when(mockRepository.findById("648717e7a4bbf16c36eaa21d")).thenReturn(Optional.of(abrigo));

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(uri+"/648717e7a4bbf16c36eaa21d")
                                .header("authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgQWRvcGV0Iiwic3ViIjoiYWRtaW4iLCJleHAiOjE2ODY4MzkzMjF9.pLynyH-V-L0j8s68UCU9TzBvCwDG4VhHkzb3GZis3iI")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().is(200));
    }


}
