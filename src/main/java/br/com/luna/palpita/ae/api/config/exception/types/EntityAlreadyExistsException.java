package br.com.luna.palpita.ae.api.config.exception.types;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityAlreadyExistsException extends RuntimeException {
    private final String identifier;

    public EntityAlreadyExistsException(String message, String identifier) {
        super(message);
        this.identifier = identifier;
    }
}
