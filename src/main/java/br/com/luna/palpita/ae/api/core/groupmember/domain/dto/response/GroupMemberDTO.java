package br.com.luna.palpita.ae.api.core.groupmember.domain.dto.response;

import br.com.luna.palpita.ae.api.core.predictiongroup.domain.dto.response.PredictionGroupDTO;
import br.com.luna.palpita.ae.api.core.profile.domain.dto.response.ProfileDTO;
import br.com.luna.palpita.ae.api.core.role.Role;

import java.time.LocalDateTime;

public record GroupMemberDTO(
        Long id,
        PredictionGroupDTO predictionGroup,
        ProfileDTO profile,
        Role role,
        LocalDateTime joinedAt
) {
}
