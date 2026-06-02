package br.com.luna.palpita_ae_api.core.predictiongroup.mapper;

import br.com.luna.palpita_ae_api.core.predictiongroup.domain.dto.request.PredictionGroupFormDTO;
import br.com.luna.palpita_ae_api.core.predictiongroup.domain.entity.PredictionGroup;
import org.springframework.stereotype.Component;

@Component
public class PredictionGroupUpdateMapper {

    public PredictionGroup update(PredictionGroup predictionGroup, PredictionGroupFormDTO predictionGroupFormDTO) {
        predictionGroup.setName(predictionGroupFormDTO.name());
        predictionGroup.setDescription(predictionGroupFormDTO.description());
        predictionGroup.setIsPrivate(predictionGroupFormDTO.isPrivate());
        predictionGroup.setPassword(predictionGroupFormDTO.password());
        predictionGroup.setInviteCode(predictionGroupFormDTO.inviteCode());
        return predictionGroup;
    }
}