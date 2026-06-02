package br.com.luna.palpita_ae_api.core.profile.domain.dto.response;

import java.time.LocalDateTime;

public record ProfileDTO(
        Long id,
        String name,
        String nickname,
        String avatarUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
