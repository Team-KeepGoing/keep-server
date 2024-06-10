package com.keepgoing.keepserver.domain.rent.presentation;

import com.keepgoing.keepserver.domain.rent.service.RentService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "대여", description = "대여 관련 api 입니다.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rent")
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;
    @Operation(summary = "기자재 대여", description = "기자재를 대여합니다.")
    @PostMapping("/device")
    public BaseResponse rentDevice(@RequestParam String deviceName, @RequestParam String email) {
        return rentService.rentDevice(deviceName, email);
    }

    @Operation(summary = "도서 대여", description = "기자재를 대여합니다.")
    @PostMapping("/book")
    public BaseResponse rentBook(@RequestParam String bookName, @RequestParam String email) {
        return rentService.rentBook(bookName, email);
    }
}
