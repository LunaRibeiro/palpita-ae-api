package br.com.luna.palpita.ae.api.core.profile.mapper;

import br.com.luna.palpita.ae.api.core.profile.domain.dto.request.ProfileFormDTO;
import br.com.luna.palpita.ae.api.core.profile.domain.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileUpdateMapper {

    public Profile update(Profile profile, ProfileFormDTO profileFormDTO) {
        profile.setName(profileFormDTO.name());
        profile.setEmail(profileFormDTO.email());
        profile.setAvatarUrl(profileFormDTO.avatarUrl());
        profile.setPassword(profileFormDTO.password());
        profile.setNickname(profileFormDTO.nickname());

        return profile;
    }

}
