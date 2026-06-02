package br.com.luna.palpita.ae.api.core.predictiongroup.mapper;

import br.com.luna.palpita.ae.api.core.predictiongroup.domain.dto.request.PredictionGroupFormDTO;
import br.com.luna.palpita.ae.api.core.predictiongroup.domain.entity.PredictionGroup;
import br.com.luna.palpita.ae.api.core.profile.domain.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class PredictionGroupCreateMapper {

    public PredictionGroup convert(PredictionGroupFormDTO predictionGroupFormDTO, Profile profile) {
        PredictionGroup predictionGroup = new PredictionGroup();

        predictionGroup.setName(predictionGroupFormDTO.name());
        predictionGroup.setDescription(predictionGroupFormDTO.description());
        predictionGroup.setOwnerId(profile);
        predictionGroup.setInviteCode(predictionGroupFormDTO.inviteCode());
        predictionGroup.setPassword(predictionGroupFormDTO.password());
        predictionGroup.setIsPrivate(predictionGroupFormDTO.isPrivate());
        return predictionGroup;
    }

}
