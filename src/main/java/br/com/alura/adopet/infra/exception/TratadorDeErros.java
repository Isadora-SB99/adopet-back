package br.com.alura.adopet.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity tratarErro403(){
        return ResponseEntity.badRequest().body("Não tem autorização");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }

//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity usuarioNaoEncontrado(UsernameNotFoundException ex){
////        return ResponseEntity.status(404).body("Usuario não cadastrado");
//        return ResponseEntity.status(404).body(ex.getMessage());
//    }

}
