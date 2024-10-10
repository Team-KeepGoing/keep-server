package com.keepgoing.keepserver.domain.damage.presentation;

import com.keepgoing.keepserver.domain.damage.payload.request.DamageCreateRequest;
import com.keepgoing.keepserver.domain.damage.service.DamageService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "파손", description = " 관련 api 입니다.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/damage")
public class DamageController {
    private final DamageService damageService;

    @Operation(summary = "파손 등록", description = "파손된 항목 등록을 진행합니다.")
    @PostMapping("/report")
    public BaseResponse reportDamage(@Valid @RequestBody DamageCreateRequest request) {
        return damageService.reportDamage(request);
    }

    @Operation(summary = "파손 정보 불러오기", description = "파손된 항목들을 불러옵니다.")
    @GetMapping("/all")
    public BaseResponse getAllDamages() {
        return damageService.getAllDamages();
    }

    @Operation(summary = "파손 정보 삭제하기", description = "파손된 항목을 삭제합니다.")
    @DeleteMapping("/{id}")
    public BaseResponse deleteDamage(@PathVariable Long id) {
        return damageService.deleteDamage(id);
    }
}
