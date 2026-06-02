package br.com.luna.palpita.ae.api.core.predictiongroup.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PredictionGroupFormDTO(
        Long ownerId,
        @NotBlank
        String name,
        String description,
        @NotBlank
        String inviteCode,
        String password,
        Boolean isPrivate
) {
}
