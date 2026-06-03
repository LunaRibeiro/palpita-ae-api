package br.com.luna.palpita.ae.api.core.groupmember.controller;

import br.com.luna.palpita.ae.api.common.utils.HttpUtils;
import br.com.luna.palpita.ae.api.core.groupmember.domain.dto.request.GroupMemberFilterDTO;
import br.com.luna.palpita.ae.api.core.groupmember.domain.dto.request.GroupMemberFormDTO;
import br.com.luna.palpita.ae.api.core.groupmember.domain.dto.response.GroupMemberDTO;
import br.com.luna.palpita.ae.api.core.groupmember.domain.entity.GroupMember;
import br.com.luna.palpita.ae.api.core.groupmember.service.GroupMemberService;
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
@RequestMapping("/group-member")
public class GroupMemberController {

    private final GroupMemberService groupMemberService;
    private final ProfileService profileService;
    private final PredictionGroupService predictionGroupService;

    @GetMapping
    public ResponseEntity<Page<GroupMemberDTO>> listPaged(Pageable pageable, GroupMemberFilterDTO groupMemberFilterDTO) {
        Page<GroupMember> groupMemberPage = groupMemberService.list(pageable, groupMemberFilterDTO);
        Page<GroupMemberDTO> groupMemberDTOPage = groupMemberService.generateGroupMemberDTOPage(groupMemberPage);

        return ResponseEntity.ok(groupMemberDTOPage);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GroupMemberDTO>> list(GroupMemberFilterDTO groupMemberFilterDTO) {
        List<GroupMember> groupMemberList = groupMemberService.list(groupMemberFilterDTO);
        List<GroupMemberDTO> groupMemberDTOList = groupMemberService.generateGroupMemberDTOList(groupMemberList);

        return ResponseEntity.ok(groupMemberDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupMemberDTO> get(@PathVariable Long id) {
        GroupMember groupMember = groupMemberService.getOrNull(id);
        if (groupMember == null) return ResponseEntity.notFound().build();

        GroupMemberDTO groupMemberDTO = groupMemberService.generateGroupMemberDTO(groupMember);

        return ResponseEntity.ok(groupMemberDTO);
    }

    @PostMapping
    public ResponseEntity<GroupMemberDTO> create(@RequestBody @Valid GroupMemberFormDTO groupMemberFormDTO, UriComponentsBuilder uriComponentsBuilder) {
        Profile profile = profileService.getOrThrowException(groupMemberFormDTO.profileId());
        PredictionGroup predictionGroup = predictionGroupService.getOrThrowException(groupMemberFormDTO.groupId());

        GroupMember groupMember = groupMemberService.generateGroupMember(groupMemberFormDTO, profile, predictionGroup);
        groupMemberService.save(groupMember);

        URI uri = HttpUtils.createURI(uriComponentsBuilder, "group-member", groupMember.getId());

        return ResponseEntity.created(uri).body(groupMemberService.generateGroupMemberDTO(groupMember));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid GroupMemberFormDTO groupMemberFormDTO) {
        GroupMember groupMember = groupMemberService.getOrThrowException(id);

        groupMemberService.updateGroupMember(groupMember, groupMemberFormDTO);
        groupMemberService.save(groupMember);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        GroupMember groupMember = groupMemberService.getOrThrowException(id);

        groupMemberService.delete(groupMember);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists-member")
    public ResponseEntity<Boolean> existsMember(@RequestParam(required = false) Long id, @RequestParam Long groupId, @RequestParam Long profileId) {
        return ResponseEntity.ok(groupMemberService.existsMember(groupId, profileId, id));
    }
}
