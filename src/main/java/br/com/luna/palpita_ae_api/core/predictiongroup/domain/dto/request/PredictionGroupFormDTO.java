package br.com.luna.palpita_ae_api.core.predictiongroup.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record PredictionGroupFormDTO(
        Long ownerId,
        @NotBlank
        String name,
        String description,
        @NotBlank
        String inviteCode,
        String password,
        Boolean isPrivate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
