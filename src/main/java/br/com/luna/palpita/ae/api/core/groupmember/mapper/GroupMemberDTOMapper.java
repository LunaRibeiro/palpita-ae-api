package br.com.luna.palpita.ae.api.core.groupmember.mapper;

import br.com.luna.palpita.ae.api.core.groupmember.domain.dto.response.GroupMemberDTO;
import br.com.luna.palpita.ae.api.core.groupmember.domain.entity.GroupMember;
import br.com.luna.palpita.ae.api.core.predictiongroup.mapper.PredictionGroupDTOMapper;
import br.com.luna.palpita.ae.api.core.profile.mapper.ProfileDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GroupMemberDTOMapper {

    private final PredictionGroupDTOMapper predictionGroupDTOMapper;
    private final ProfileDTOMapper profileDTOMapper;

    public GroupMemberDTO convert(GroupMember groupMember) {
        return new GroupMemberDTO(
                groupMember.getId(),
                predictionGroupDTOMapper.convert(groupMember.getGroup()),
                profileDTOMapper.convert(groupMember.getProfile()),
                groupMember.getRole(),
                groupMember.getJoinedAt()
        );
    }
}