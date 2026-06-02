package br.com.luna.palpita_ae_api.core.predictiongroup.domain.entity;

import br.com.luna.palpita_ae_api.core.profile.domain.entity.Profile;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PredictionGroup {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Profile ownerId;
    private String name;
    private String description;
    private String inviteCode;
    private String password;
    private Boolean isPrivate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
