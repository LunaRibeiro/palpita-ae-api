package br.com.luna.palpita_ae_api.core.groupmember.domain.entity;

import br.com.luna.palpita_ae_api.core.predictiongroup.domain.entity.PredictionGroup;
import br.com.luna.palpita_ae_api.core.profile.domain.entity.Profile;
import br.com.luna.palpita_ae_api.core.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupMember {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private PredictionGroup group;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime joinedAt;
}
