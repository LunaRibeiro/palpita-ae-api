package br.com.luna.palpita.ae.api.core.predictiongroup.service;

import br.com.luna.palpita.ae.api.common.specification.SearchCriteria;
import br.com.luna.palpita.ae.api.common.specification.SpecificationHelper;
import br.com.luna.palpita.ae.api.config.exception.types.EntityNotFoundException;
import br.com.luna.palpita.ae.api.core.predictiongroup.domain.dto.request.PredictionGroupFilterDTO;
import br.com.luna.palpita.ae.api.core.predictiongroup.domain.dto.request.PredictionGroupFormDTO;
import br.com.luna.palpita.ae.api.core.predictiongroup.domain.dto.response.PredictionGroupDTO;
import br.com.luna.palpita.ae.api.core.predictiongroup.domain.entity.PredictionGroup;
import br.com.luna.palpita.ae.api.core.predictiongroup.mapper.PredictionGroupCreateMapper;
import br.com.luna.palpita.ae.api.core.predictiongroup.mapper.PredictionGroupDTOMapper;
import br.com.luna.palpita.ae.api.core.predictiongroup.mapper.PredictionGroupUpdateMapper;
import br.com.luna.palpita.ae.api.core.predictiongroup.repository.PredictionGroupRepository;
import br.com.luna.palpita.ae.api.core.predictiongroup.specification.PredictionGroupSpecification;
import br.com.luna.palpita.ae.api.core.profile.domain.entity.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictionGroupService {

    private final PredictionGroupRepository predictionGroupRepository;
    private final PredictionGroupCreateMapper predictionGroupCreateMapper;
    private final PredictionGroupDTOMapper predictionGroupDTOMapper;
    private final PredictionGroupUpdateMapper predictionGroupUpdateMapper;

    public PredictionGroup generatePredictionGroup(PredictionGroupFormDTO predictionGroupFormDTO, Profile profile) {
        return predictionGroupCreateMapper.convert(predictionGroupFormDTO, profile);
    }

    public void save (PredictionGroup predictionGroup) {
        predictionGroupRepository.save(predictionGroup);
    }

    public PredictionGroupDTO generatePredictionGroupDTO(PredictionGroup predictionGroup) {
        return predictionGroupDTOMapper.convert(predictionGroup);
    }

    public PredictionGroup getOrNull(Long id){
        if (id == null) return null;
        return predictionGroupRepository.findById(id).orElse(null);
    }

    public void updatePredictionGroup(PredictionGroup predictionGroup, PredictionGroupFormDTO predictionGroupFormDTO) {
        predictionGroupUpdateMapper.update(predictionGroup, predictionGroupFormDTO);
    }

    public void delete(PredictionGroup predictionGroup) {
        predictionGroupRepository.delete(predictionGroup);
    }

    public List<PredictionGroup> list(PredictionGroupFilterDTO predictionGroupFilterDTO) {
        Specification<PredictionGroup> predictionGroupSpecification = generateSpecification(predictionGroupFilterDTO);
        return predictionGroupRepository.findAll(predictionGroupSpecification);
    }

    public Page<PredictionGroup> list(Pageable pageable, PredictionGroupFilterDTO predictionGroupFilterDTO) {
        Specification<PredictionGroup> predictionGroupSpecification = generateSpecification(predictionGroupFilterDTO);
        return predictionGroupRepository.findAll(predictionGroupSpecification, pageable);
    }

    private Specification<PredictionGroup> generateSpecification(PredictionGroupFilterDTO predictionGroupFilterDTO) {
        SearchCriteria<String> nameCriteria = SpecificationHelper.generateInnerLikeCriteria("name", predictionGroupFilterDTO.name());
        SearchCriteria<Long> ownerIdCriteria = SpecificationHelper.generateEqualsCriteria("owner.id", predictionGroupFilterDTO.ownerId());

        Specification<PredictionGroup> nameSpecification = new PredictionGroupSpecification(nameCriteria);
        Specification<PredictionGroup> ownerIdSpecification = new PredictionGroupSpecification(ownerIdCriteria);

        return Specification.where(nameSpecification)
                .and(ownerIdSpecification);
    }

    public Page<PredictionGroupDTO> generatePredictionGroupDTOPage(Page<PredictionGroup> predictionGroupPage) {
        return predictionGroupPage.map(this::generatePredictionGroupDTO);
    }

    public List<PredictionGroupDTO> generatePredictionGroupDTOList(List<PredictionGroup> predictionGroupList) {
        return predictionGroupList.stream().map(predictionGroupDTOMapper::convert).toList();
    }

    public PredictionGroup getOrThrowException(Long id) {
        return predictionGroupRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("PredictionGroup", id)
        );
    }

    public boolean existsByName(Long id, String name) {

        SearchCriteria<String> nameCriteria = SpecificationHelper.generateEqualsCaseInsensitiveCriteria("name", name);

        Specification<PredictionGroup> nameSpecification = new PredictionGroupSpecification(nameCriteria);

        Specification<PredictionGroup> idSpecification = SpecificationHelper.generateIdNotSpecification(id);
        Specification<PredictionGroup> specification = nameSpecification.and(idSpecification);

        return predictionGroupRepository.exists(specification);
    }

    public boolean existsByInviteCode(Long id, String inviteCode) {

        SearchCriteria<String> inviteCodeCriteria = SpecificationHelper.generateEqualsCaseInsensitiveCriteria("inviteCode", inviteCode);

        Specification<PredictionGroup> inviteCodeSpecification = new PredictionGroupSpecification(inviteCodeCriteria);

        Specification<PredictionGroup> idSpecification = SpecificationHelper.generateIdNotSpecification(id);

        Specification<PredictionGroup> specification =
                inviteCodeSpecification.and(idSpecification);

        return predictionGroupRepository.exists(specification);
    }
}
