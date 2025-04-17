package com.keepgoing.keepserver.domain.teacher.presentation;

import com.keepgoing.keepserver.domain.teacher.payload.request.ItemRequest;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemStatusUpdateRequest;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemUpdateRequest;
import com.keepgoing.keepserver.domain.teacher.service.ItemService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final ItemService itemService;
    private static final MediaType CONTENT_TYPE_XLSX = MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    @GetMapping("/item/list")
    public BaseResponse allItems() {
        return itemService.findAll();
    }

    @PostMapping("/item")
    public BaseResponse createItem(@RequestBody ItemRequest request) {
        return itemService.createItem(request);
    }

    @GetMapping("/item/{id}")
    public BaseResponse readItem(@PathVariable Long id) {
        return itemService.readItem(id);
    }

    @PatchMapping("/item/{id}")
    public BaseResponse updateItem(@PathVariable Long id, @RequestBody ItemUpdateRequest request) {
        return itemService.updateItem(id, request);
    }

    @GetMapping("/item/count")
    public BaseResponse statusCount() {
        return itemService.statusCount();
    }

    @PatchMapping("/item/status")
    public BaseResponse updateItemStatus(@RequestBody ItemStatusUpdateRequest request) {
        return itemService.updateItemStatus(request);
    }

    @PostMapping("/item/import")
    public BaseResponse importItemsFromExcel(@RequestPart(value = "excel") MultipartFile file) {
        return itemService.importItemsFromExcel(file);
    }

    @PostMapping("/item/validate")
    public BaseResponse validateItemsFromExcel(@RequestPart(value = "excel") MultipartFile file) {
        return itemService.validateItemsFromExcel(file);
    }

    @GetMapping("/item/template")
    public ResponseEntity<Resource> downloadItemTemplateFile() {
        var body = itemService.downloadItemTemplateFile();
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dgsw-item-template.xlsx")
                             .contentType(CONTENT_TYPE_XLSX)
                             .body(body);
    }

    @GetMapping("/item/export")
    public ResponseEntity<Resource> exportItemsToExcelFile() {
        var body = itemService.exportItemsToExcelFile();
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dgsw-item-list.xlsx")
                             .contentType(CONTENT_TYPE_XLSX)
                             .body(body);
    }
}
