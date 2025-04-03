package com.keepgoing.keepserver.domain.teacher.presentation;

import com.keepgoing.keepserver.domain.teacher.payload.request.ItemRequest;
import com.keepgoing.keepserver.domain.teacher.service.ItemService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final ItemService itemService;

    @GetMapping("/item/list")
    public BaseResponse allItems(){
        return itemService.findAll();
    }

    @PostMapping("/item")
    public BaseResponse createItem(@RequestBody ItemRequest request){
        return itemService.createItem(request);
    }

    @GetMapping("/item/{id}")
    public BaseResponse readItem(@PathVariable("id") Long id){
        return itemService.readItem(id);
    }


}
