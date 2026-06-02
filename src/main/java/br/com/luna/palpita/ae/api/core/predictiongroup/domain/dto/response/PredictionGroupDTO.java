package br.com.luna.palpita.ae.api.core.predictiongroup.domain.dto.response;

import br.com.luna.palpita.ae.api.core.profile.domain.dto.response.ProfileDTO;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record PredictionGroupDTO(
        Long id,
        ProfileDTO profile,
        @NotBlank
        String name,
        String description,
        @NotBlank
        String inviteCode,
        String password,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
