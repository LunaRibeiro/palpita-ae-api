package br.com.luna.palpita.ae.api.core.predictiongroup.controller;

import br.com.luna.palpita.ae.api.common.utils.HttpUtils;
import br.com.luna.palpita.ae.api.core.predictiongroup.domain.dto.request.PredictionGroupFilterDTO;
import br.com.luna.palpita.ae.api.core.predictiongroup.domain.dto.request.PredictionGroupFormDTO;
import br.com.luna.palpita.ae.api.core.predictiongroup.domain.dto.response.PredictionGroupDTO;
import br.com.luna.palpita.ae.api.core.predictiongroup.domain.entity.PredictionGroup;
import br.com.luna.palpita.ae.api.core.predictiongroup.service.PredictionGroupService;
import br.com.luna.palpita.ae.api.core.profile.domain.entity.Profile;
import br.com.luna.palpita.ae.api.core.profile.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prediction-group")
public class PredictionGroupController {

    private final PredictionGroupService predictionGroupService;
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<Page<PredictionGroupDTO>> listPaged(
            Pageable pageable,
            PredictionGroupFilterDTO predictionGroupFilterDTO
    ) {
        Page<PredictionGroup> predictionGroupPage = predictionGroupService.list(pageable, predictionGroupFilterDTO);
        Page<PredictionGroupDTO> predictionGroupDTOPage = predictionGroupService.generatePredictionGroupDTOPage(predictionGroupPage);

        return ResponseEntity.ok(predictionGroupDTOPage);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PredictionGroupDTO>> list(PredictionGroupFilterDTO predictionGroupFilterDTO) {
        List<PredictionGroup> predictionGroupList = predictionGroupService.list(predictionGroupFilterDTO);
        List<PredictionGroupDTO> predictionGroupDTOList = predictionGroupService.generatePredictionGroupDTOList(predictionGroupList);

        return ResponseEntity.ok(predictionGroupDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredictionGroupDTO> get(@PathVariable Long id) {
        PredictionGroup predictionGroup = predictionGroupService.getOrNull(id);
        if (predictionGroup == null) return ResponseEntity.notFound().build();

        PredictionGroupDTO predictionGroupDTO = predictionGroupService.generatePredictionGroupDTO(predictionGroup);

        return ResponseEntity.ok(predictionGroupDTO);
    }

    @PostMapping
    public ResponseEntity<PredictionGroupDTO> create(@RequestBody @Valid PredictionGroupFormDTO predictionGroupFormDTO, UriComponentsBuilder uriComponentsBuilder) {
        Profile profile = profileService.getOrThrowException(predictionGroupFormDTO.ownerId());

        PredictionGroup predictionGroup = predictionGroupService.generatePredictionGroup(predictionGroupFormDTO, profile);
        predictionGroupService.save(predictionGroup);

        URI uri = HttpUtils.createURI(uriComponentsBuilder, "prediction-group", predictionGroup.getId());

        return ResponseEntity.created(uri).body(predictionGroupService.generatePredictionGroupDTO(predictionGroup));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid PredictionGroupFormDTO predictionGroupFormDTO) {
        PredictionGroup predictionGroup = predictionGroupService.getOrThrowException(id);

        predictionGroupService.updatePredictionGroup(predictionGroup, predictionGroupFormDTO);
        predictionGroupService.save(predictionGroup);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        PredictionGroup predictionGroup = predictionGroupService.getOrThrowException(id);

        predictionGroupService.delete(predictionGroup);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists-by-name")
    public ResponseEntity<Boolean> existsByName(@RequestParam(required = false) Long id, @RequestParam String name) {
        return ResponseEntity.ok(predictionGroupService.existsByName(id, name));
    }

    @GetMapping("/exists-by-invite-code")
    public ResponseEntity<Boolean> existsByInviteCode(@RequestParam(required = false) Long id, @RequestParam String inviteCode) {
        return ResponseEntity.ok(predictionGroupService.existsByInviteCode(id, inviteCode));
    }
}
