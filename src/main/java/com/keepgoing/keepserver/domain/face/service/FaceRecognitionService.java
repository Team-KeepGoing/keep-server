package com.keepgoing.keepserver.domain.face.service;

import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FaceRecognitionService {
    BaseResponse compareFaces(MultipartFile sourceImage);

    BaseResponse registerFace(String email, MultipartFile image);
}