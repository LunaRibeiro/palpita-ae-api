package br.com.luna.palpita_ae_api.core.profile.mapper;

import br.com.luna.palpita_ae_api.core.profile.domain.dto.response.ProfileDTO;
import br.com.luna.palpita_ae_api.core.profile.domain.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileDTOMapper {

    public ProfileDTO convert(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                profile.getName(),
                profile.getNickname(),
                profile.getAvatarUrl(),
                profile.getCreatedAt(),
                profile.getUpdatedAt()
        );
    }
}