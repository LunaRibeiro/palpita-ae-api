package br.com.luna.palpita.ae.api.core.groupmember.mapper;

import br.com.luna.palpita.ae.api.core.groupmember.domain.dto.request.GroupMemberFormDTO;
import br.com.luna.palpita.ae.api.core.groupmember.domain.entity.GroupMember;
import br.com.luna.palpita.ae.api.core.predictiongroup.domain.entity.PredictionGroup;
import br.com.luna.palpita.ae.api.core.profile.domain.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class GroupMemberCreateMapper {

    public GroupMember convert(GroupMemberFormDTO groupMemberFormDTO, Profile profile, PredictionGroup predictionGroup) {
        GroupMember groupMember = new GroupMember();

        groupMember.setRole(groupMemberFormDTO.role());
        groupMember.setProfile(profile);
        groupMember.setGroup(predictionGroup);
        groupMember.setJoinedAt(groupMemberFormDTO.joinedAt());

        return groupMember;
    }

}
