package br.com.luna.palpita.ae.api.config.exception.handler;

import br.com.luna.palpita.ae.api.config.exception.domain.Error;
import br.com.luna.palpita.ae.api.config.exception.types.EntityAlreadyExistsException;
import br.com.luna.palpita.ae.api.config.exception.types.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Error> handleConstraintViolationException(ConstraintViolationException e) {
        Error error = new Error(
                "Violação de restrição do banco de dados",
                e.getSQLException().getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")),
                new ArrayList<>()
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = {EntityAlreadyExistsException.class})
    public ResponseEntity<Error> handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        Error error = new Error(
                "Entidade já existe",
                e.getMessage() + ": " + e.getIdentifier(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")),
                new ArrayList<>()
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Error> handleEntityNotFoundException(EntityNotFoundException e) {
        String entity = e.getEntity();
        String message = "não foi possível encontrar " + e.getEntity() + " com o identificador " + e.getId();
        String messageEN = "Unable to find " + entity + " with identifier " + e.getId();

        Error error = new Error(
                message,
                messageEN,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z")),
                new ArrayList<>()
        );
        return ResponseEntity.badRequest().body(error);
    }

}
