package br.com.luna.palpita.ae.api.core.groupmember.domain.dto.request;

import br.com.luna.palpita.ae.api.core.role.Role;

import java.time.LocalDateTime;

public record GroupMemberFormDTO(
        Long groupId,
        Long profileId,
        Role role,
        LocalDateTime joinedAt
) {
}
