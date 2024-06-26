package com.keepgoing.keepserver.domain.Return.presentation;

import com.keepgoing.keepserver.domain.Return.service.ReturnService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "반납", description = "반납 관련 api 입니다.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/return")
@RequiredArgsConstructor
public class ReturnController {
    private final ReturnService returnService;

    @Operation(summary = "기자재 반납", description = "기자재 이름과 이메일을 통해 기자재를 반납합니다.")
    @PostMapping("/device")
    public BaseResponse returnDevice(@RequestParam String deviceName, @RequestParam String email) {
        return returnService.returnDevice(deviceName, email);
    }

    @Operation(summary = "도서 반납", description = "책 제목과 이메일을 통해 도서를 반납합니다.")
    @PostMapping("/book")
    public BaseResponse returnBook(@RequestParam String nfcCode, @RequestParam String email) {
        return returnService.returnBook(nfcCode, email);
    }
}
