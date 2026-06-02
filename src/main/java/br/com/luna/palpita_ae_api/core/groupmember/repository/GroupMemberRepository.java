package br.com.luna.palpita_ae_api.core.groupmember.repository;

import br.com.luna.palpita_ae_api.core.groupmember.domain.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long>, JpaSpecificationExecutor<GroupMember> {
}
