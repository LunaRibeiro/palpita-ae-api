package br.com.luna.palpita_ae_api.config.exception.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FormFieldError implements SubError {
    private String fieldPT;
    private Object rejectedValue;
    private String messagePT;
}
