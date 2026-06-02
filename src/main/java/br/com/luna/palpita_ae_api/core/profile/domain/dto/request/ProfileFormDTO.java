package br.com.luna.palpita_ae_api.core.profile.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ProfileFormDTO(
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotBlank
        String name,
        @NotBlank
        String nickname,
        String avatarUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
