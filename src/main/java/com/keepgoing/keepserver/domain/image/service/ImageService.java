package com.keepgoing.keepserver.domain.image.service;

import com.keepgoing.keepserver.domain.image.dto.ImageDTO;
import com.keepgoing.keepserver.global.common.S3.S3Uploader;
import com.keepgoing.keepserver.global.exception.BusinessException;
import com.keepgoing.keepserver.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final S3Uploader s3Uploader;

    public ImageDTO uploadImage(MultipartFile multipartFile) {
        try {
            return new ImageDTO(s3Uploader.upload(multipartFile, "picture"));
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.FILE_ERROR);
        }
    }
}

