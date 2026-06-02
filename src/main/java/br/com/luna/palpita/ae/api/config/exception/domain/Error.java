package br.com.luna.palpita.ae.api.config.exception.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Error {
    private final String message;
    private final String messageEN;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
    private List<SubError> subErrorList;
}
