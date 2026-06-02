package br.com.luna.palpita.ae.api.core.groupmember.mapper;

import br.com.luna.palpita.ae.api.core.groupmember.domain.dto.request.GroupMemberFormDTO;
import br.com.luna.palpita.ae.api.core.groupmember.domain.entity.GroupMember;
import org.springframework.stereotype.Component;

@Component
public class GroupMemberUpdateMapper {

    public GroupMember update(GroupMember groupMember, GroupMemberFormDTO groupMemberFormDTO) {

        groupMember.setRole(groupMemberFormDTO.role());

        return groupMember;
    }
}
