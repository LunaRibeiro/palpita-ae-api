package br.com.luna.palpita.ae.api.core.profile.domain.dto.response;

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
