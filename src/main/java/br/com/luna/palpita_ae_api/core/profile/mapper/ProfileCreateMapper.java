package br.com.luna.palpita_ae_api.core.profile.mapper;

import br.com.luna.palpita_ae_api.core.profile.domain.dto.request.ProfileFormDTO;
import br.com.luna.palpita_ae_api.core.profile.domain.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileCreateMapper {

    public Profile convert(ProfileFormDTO profileFormDTO) {
        Profile profile = new Profile();
        profile.setName(profileFormDTO.name());
        profile.setNickname(profileFormDTO.nickname());
        profile.setEmail(profileFormDTO.email());
        profile.setPassword(profileFormDTO.password());
        profile.setAvatarUrl(profileFormDTO.avatarUrl());
        return profile;
    }
}
