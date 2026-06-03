package br.com.luna.palpita.ae.api.core.profile.service;

import br.com.luna.palpita.ae.api.common.specification.SearchCriteria;
import br.com.luna.palpita.ae.api.common.specification.SpecificationHelper;
import br.com.luna.palpita.ae.api.config.exception.types.EntityNotFoundException;
import br.com.luna.palpita.ae.api.core.profile.domain.dto.request.ProfileFilterDTO;
import br.com.luna.palpita.ae.api.core.profile.domain.dto.request.ProfileFormDTO;
import br.com.luna.palpita.ae.api.core.profile.domain.dto.response.ProfileDTO;
import br.com.luna.palpita.ae.api.core.profile.domain.entity.Profile;
import br.com.luna.palpita.ae.api.core.profile.mapper.ProfileCreateMapper;
import br.com.luna.palpita.ae.api.core.profile.mapper.ProfileDTOMapper;
import br.com.luna.palpita.ae.api.core.profile.mapper.ProfileUpdateMapper;
import br.com.luna.palpita.ae.api.core.profile.repository.ProfileRepository;
import br.com.luna.palpita.ae.api.core.profile.specification.ProfileSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileCreateMapper profileCreateMapper;
    private final ProfileDTOMapper profileDTOMapper;
    private final ProfileUpdateMapper profileUpdateMapper;

    public Profile generateProfile(ProfileFormDTO profileFormDTO) {
        return profileCreateMapper.convert(profileFormDTO);
    }

    public Profile save (Profile profile) {
        return profileRepository.save(profile);
    }

    public ProfileDTO generateProfileDTO(Profile profile) {
        return profileDTOMapper.convert(profile);
    }

    public Profile getOrNull(Long id){
        if (id == null) return null;
        return profileRepository.findById(id).orElse(null);
    }

    public void updateProfile(Profile student, ProfileFormDTO profileFormDTO) {
        profileUpdateMapper.update(student, profileFormDTO);
    }

    public void delete(Profile profile) {
        profileRepository.delete(profile);
    }

    public List<Profile> list(ProfileFilterDTO profileFilterDTO) {
        Specification<Profile> profileSpecification = generateSpecification(profileFilterDTO);
        return profileRepository.findAll(profileSpecification);
    }

    public Page<Profile> list(Pageable pageable, ProfileFilterDTO profileFilterDTO) {
        Specification<Profile> profileSpecification = generateSpecification(profileFilterDTO);
        return profileRepository.findAll(profileSpecification, pageable);
    }

    private Specification<Profile> generateSpecification(ProfileFilterDTO profileFilterDTO) {
        SearchCriteria<String> nameCriteria = SpecificationHelper.generateInnerLikeCriteria("name", profileFilterDTO.name());
        SearchCriteria<String> nicknameCriteria = SpecificationHelper.generateInnerLikeCriteria("nickname", profileFilterDTO.nickname());

        Specification<Profile> nameSpecification = new ProfileSpecification(nameCriteria);
        Specification<Profile> nicknameSpecification = new ProfileSpecification(nicknameCriteria);

        return Specification.where(nameSpecification)
                .and(nicknameSpecification);
    }

    public Page<ProfileDTO> generateProfileDTOPage(Page<Profile> profilePage) {
        return profilePage.map(this::generateProfileDTO);
    }

    public List<ProfileDTO> generateProfileDTOList(List<Profile> profileList) {
        return profileList.stream().map(profileDTOMapper::convert).toList();
    }

    public Profile getOrThrowException(Long id) {
        return profileRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Profile", id)
        );
    }
}