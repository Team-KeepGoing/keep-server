package com.keepgoing.keepserver.domain.teacher.presentation;

import com.keepgoing.keepserver.domain.teacher.payload.request.ItemRequest;
import com.keepgoing.keepserver.domain.teacher.service.ItemService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "교사용", description = "교사용 관리 api 입니다.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final ItemService itemService;

    @Operation(summary = "관리 물품 리스트 조회", description = "관리 중인 물품 리스트를 조회합니다.")
    @GetMapping("/item/list")
    public BaseResponse allItems(){
        return itemService.findAll();
    }

    @Operation(summary = "관리 물품 직접 추가", description = "관리할 물품을 직접 추가합니다.")
    @PostMapping("/item")
    public BaseResponse createItem(@RequestBody ItemRequest request){
        return itemService.createItem(request);
    }

}
