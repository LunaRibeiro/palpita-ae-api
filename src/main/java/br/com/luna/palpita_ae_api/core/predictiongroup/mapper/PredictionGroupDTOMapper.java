package br.com.luna.palpita_ae_api.core.predictiongroup.mapper;

import br.com.luna.palpita_ae_api.core.predictiongroup.domain.dto.response.PredictionGroupDTO;
import br.com.luna.palpita_ae_api.core.predictiongroup.domain.entity.PredictionGroup;
import br.com.luna.palpita_ae_api.core.profile.mapper.ProfileDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PredictionGroupDTOMapper {

    private final ProfileDTOMapper profileDTOMapper;

    public PredictionGroupDTO convert(PredictionGroup predictionGroup) {
        return new PredictionGroupDTO(
                predictionGroup.getId(),
                profileDTOMapper.convert(predictionGroup.getOwnerId()),
                predictionGroup.getName(),
                predictionGroup.getDescription(),
                predictionGroup.getInviteCode(),
                predictionGroup.getPassword(),
                predictionGroup.getCreatedAt(),
                predictionGroup.getUpdatedAt()
        );
    }
}