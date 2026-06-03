package br.com.luna.palpita.ae.api.core.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginFormDTO(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
) {
}
