package com.keepgoing.keepserver.domain.face.controller;

import com.keepgoing.keepserver.domain.face.service.FaceRecognitionService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "얼굴인식", description = "얼굴인식 관련 api 입니다.")
@RestController
@RequestMapping("/face")
@RequiredArgsConstructor
public class FaceRecognitionController {

    private final FaceRecognitionService faceRecognitionService;

    @Operation(summary = "얼굴 인식", description = "얼굴을 인식합니다.")
    @PostMapping(value = "/compare", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public BaseResponse compareFaces(@RequestPart("image") MultipartFile image) {
        return faceRecognitionService.compareFaces(image);
    }

    @Operation(summary = "얼굴 등록", description = "얼굴을 등록합니다.")
    @PostMapping(value = "/register", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public BaseResponse registerFace(@RequestPart("email") String email, @RequestPart("image") MultipartFile image) {
        return faceRecognitionService.registerFace(email, image);
    }
}
