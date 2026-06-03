package br.com.luna.palpita.ae.api.core.auth.dto.response;

import br.com.luna.palpita.ae.api.core.profile.domain.dto.response.ProfileDTO;

public record AuthDTO(
        String token,
        ProfileDTO profile
) {
}
