package br.com.luna.palpita_ae_api.core.profile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
public class Profile {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
