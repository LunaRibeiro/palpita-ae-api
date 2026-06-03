package br.com.luna.palpita.ae.api.core.groupmember.domain.dto.request;

import br.com.luna.palpita.ae.api.core.role.Role;

public record GroupMemberFilterDTO(
        Long groupId,
        Long profileId,
        Role role
) {
}
