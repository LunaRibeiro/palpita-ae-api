package br.com.luna.palpita.ae.api.core.profile.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ProfileFormDTO(
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotBlank
        String name,
        @NotBlank
        String nickname,
        String avatarUrl
) {
}
