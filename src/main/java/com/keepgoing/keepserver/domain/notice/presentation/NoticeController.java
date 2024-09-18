package com.keepgoing.keepserver.domain.notice.presentation;

import com.keepgoing.keepserver.domain.notice.payload.req.NoticeCreateDto;
import com.keepgoing.keepserver.domain.notice.service.NoticeService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "공지", description = "공지 관련 api 입니다.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/notice")
@RequiredArgsConstructor
@RestController
public class NoticeController {
    private final NoticeService noticeService;
    @Operation(summary = "공지 등록", description = "공지 등록을 진행합니다. 전체 학생을 등록하고 싶으실 경우엔 isGlobal을 true로 해주시고, list는 빈값으로 보내시면 됩니다.")
    @PostMapping("/post")
    public BaseResponse addNotice(@Valid @RequestBody NoticeCreateDto noticeCreateDto, Authentication authentication) {
        return noticeService.createNotice(noticeCreateDto,authentication);
    }
    @Operation(summary = "공지 수정", description = "등록된 공지를 수정합니다.")
    @PatchMapping("/edit/{id}")
    public BaseResponse editNotice(@PathVariable Long id, @RequestBody NoticeCreateDto noticeCreateDto, Authentication authentication) {
        return noticeService.updateNotice(id, noticeCreateDto,authentication);
    }

    @Operation(summary = "공지 삭제", description = "등록된 공지를 삭제합니다.")
    @DeleteMapping("/{id}")
    public BaseResponse deleteNotice(@PathVariable Long id, Authentication authentication){
        return noticeService.deleteNotice(id,authentication);
    }

    @Operation(summary = "전체 공지 불러오기", description = "존재하는 모든 공지를 불러옵니다.")
    @GetMapping("/all")
    public BaseResponse allNotice(Authentication authentication){
        return noticeService.getNotice(authentication);
    }

    @Operation(summary = "선생님이 쓴 글 (본인 공지) 불러오기", description = "본인의 공지를 불러옵니다.")
    @GetMapping("/teacher-my")
    public BaseResponse getNoticeOfTeacher(Authentication authentication){
        return noticeService.getNoticeOfTeacher(authentication);
    }
}
