package com.keepgoing.keepserver.domain.damage.service;

import com.keepgoing.keepserver.domain.damage.entity.Damage;
import com.keepgoing.keepserver.domain.damage.mapper.DamageMapper;
import com.keepgoing.keepserver.domain.damage.payload.request.DamageCreateRequest;
import com.keepgoing.keepserver.domain.damage.payload.response.DamageResponseDto;
import com.keepgoing.keepserver.domain.damage.repository.DamageRepository;
import com.keepgoing.keepserver.global.common.BaseResponse;
import com.keepgoing.keepserver.global.exception.damage.DamageError;
import com.keepgoing.keepserver.global.exception.damage.DamageException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DamageServiceImpl implements DamageService {

    private final DamageRepository damageRepository;
    private final DamageMapper damageMapper;

    @Override
    @Transactional
    public BaseResponse reportDamage(DamageCreateRequest request) {
        validateDuplicate(request.getCode(), request.getIssueType());
        Damage damage = damageMapper.dtoToEntity(request);
        damageRepository.save(damage);
        return new BaseResponse(HttpStatus.OK, "파손 정보 등록 성공", damage);
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse getAllDamages() {
        List<DamageResponseDto> damages = damageRepository.findAll().stream()
                                                          .map(damageMapper::entityToDto)
                                                          .toList();
        if (damages.isEmpty()) {
            throw new DamageException(DamageError.INVALID_DAMAGE);
        }
        return new BaseResponse(HttpStatus.OK, "파손 정보 불러오기 성공", damages);
    }

    @Override
    @Transactional
    public BaseResponse deleteDamage(Long id) {
        findDamageById(id);
        damageRepository.deleteById(id);
        return new BaseResponse(HttpStatus.OK, "파손 정보 삭제 성공");
    }

    private void validateDuplicate(String code, String issueType) {
        boolean isDuplicate = damageRepository.existsByCodeAndIssueType(code, issueType);
        if (isDuplicate) {
            throw new DamageException(DamageError.DUPLICATE);
        }
    }

    private void findDamageById(Long id) {
        damageRepository.findById(id)
                               .orElseThrow(() -> new DamageException(DamageError.INVALID_DAMAGE));
    }
}
