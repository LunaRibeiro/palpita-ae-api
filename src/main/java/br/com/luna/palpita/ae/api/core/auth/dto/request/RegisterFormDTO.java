package br.com.luna.palpita.ae.api.core.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterFormDTO(
        @NotBlank
        String name,

        @NotBlank
        String nickname,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 6)
        String password
) {
}
