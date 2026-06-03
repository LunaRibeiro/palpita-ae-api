package br.com.luna.palpita.ae.api.core.groupmember.service;

import br.com.luna.palpita.ae.api.common.specification.SearchCriteria;
import br.com.luna.palpita.ae.api.common.specification.SpecificationHelper;
import br.com.luna.palpita.ae.api.config.exception.types.EntityNotFoundException;
import br.com.luna.palpita.ae.api.core.groupmember.domain.dto.request.GroupMemberFilterDTO;
import br.com.luna.palpita.ae.api.core.groupmember.domain.dto.request.GroupMemberFormDTO;
import br.com.luna.palpita.ae.api.core.groupmember.domain.dto.response.GroupMemberDTO;
import br.com.luna.palpita.ae.api.core.groupmember.domain.entity.GroupMember;
import br.com.luna.palpita.ae.api.core.groupmember.mapper.GroupMemberCreateMapper;
import br.com.luna.palpita.ae.api.core.groupmember.mapper.GroupMemberDTOMapper;
import br.com.luna.palpita.ae.api.core.groupmember.mapper.GroupMemberUpdateMapper;
import br.com.luna.palpita.ae.api.core.groupmember.repository.GroupMemberRepository;
import br.com.luna.palpita.ae.api.core.predictiongroup.domain.entity.PredictionGroup;
import br.com.luna.palpita.ae.api.core.profile.domain.entity.Profile;
import br.com.luna.palpita.ae.api.core.profile.specification.ProfileSpecification;
import br.com.luna.palpita.ae.api.core.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupMemberService {

    private final GroupMemberRepository groupMemberRepository;
    private final GroupMemberCreateMapper groupMemberCreateMapper;
    private final GroupMemberDTOMapper groupMemberDTOMapper;
    private final GroupMemberUpdateMapper groupMemberUpdateMapper;

    public GroupMember generateGroupMember(GroupMemberFormDTO groupMemberFormDTO, Profile profile, PredictionGroup predictionGroup) {
        return groupMemberCreateMapper.convert(groupMemberFormDTO, profile, predictionGroup);
    }

    public GroupMember save (GroupMember groupMember) {
        return groupMemberRepository.save(groupMember);
    }

    public GroupMemberDTO generateGroupMemberDTO(GroupMember groupMember) {
        return groupMemberDTOMapper.convert(groupMember);
    }

    public GroupMember getOrNull(Long id){
        if (id == null) return null;
        return groupMemberRepository.findById(id).orElse(null);
    }

    public void updateGroupMember(GroupMember groupMember, GroupMemberFormDTO groupMemberFormDTO) {
        groupMemberUpdateMapper.update(groupMember, groupMemberFormDTO);
    }

    public void delete(GroupMember groupMember) {
        groupMemberRepository.delete(groupMember);
    }

    public List<GroupMember> list(GroupMemberFilterDTO groupMemberFilterDTO) {
        Specification<GroupMember> groupMemberSpecification = generateSpecification(groupMemberFilterDTO);
        return groupMemberRepository.findAll(groupMemberSpecification);
    }

    public Page<GroupMember> list(Pageable pageable, GroupMemberFilterDTO groupMemberFilterDTO) {
        Specification<GroupMember> groupMemberSpecification = generateSpecification(groupMemberFilterDTO);
        return groupMemberRepository.findAll(groupMemberSpecification, pageable);
    }

    private Specification<GroupMember> generateSpecification(GroupMemberFilterDTO groupMemberFilterDTO) {
        SearchCriteria<Long> groupIdCriteria = SpecificationHelper.generateEqualsCriteria("predictionGroup.id", groupMemberFilterDTO.groupId());
        SearchCriteria<Long> profileIdCriteria = SpecificationHelper.generateEqualsCriteria("profile.id", groupMemberFilterDTO.profileId());
        SearchCriteria<Role> roleCriteria = SpecificationHelper.generateEqualsCriteria("role", groupMemberFilterDTO.role());

        Specification<GroupMember> groupIdSpecification = new ProfileSpecification(groupIdCriteria);
        Specification<GroupMember> profileIdSpecification = new ProfileSpecification(profileIdCriteria);
        Specification<GroupMember> roleSpecification = new ProfileSpecification(roleCriteria);

        return Specification.where(groupIdSpecification)
                .and(profileIdSpecification)
                .and(roleSpecification);
    }

    public Page<GroupMemberDTO> generateGroupMemberDTOPage(Page<GroupMember> groupMemberPage) {
        return groupMemberPage.map(this::generateGroupMemberDTO);
    }

    public List<GroupMemberDTO> generateGroupMemberDTOList(List<GroupMember> groupMemberList) {
        return groupMemberList.stream().map(groupMemberDTOMapper::convert).toList();
    }

    public GroupMember getOrThrowException(Long id) {
        return groupMemberRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("GroupMember", id)
        );
    }
}
