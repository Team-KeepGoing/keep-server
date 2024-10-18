package com.keepgoing.keepserver.domain.image.service;

import com.keepgoing.keepserver.domain.image.dto.ImageDTO;
import com.keepgoing.keepserver.global.common.S3.S3Uploader;
import com.keepgoing.keepserver.global.exception.BusinessException;
import com.keepgoing.keepserver.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.model.Image;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

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

    public Image getS3Image(String s3ImageUrl) throws IOException {
        byte[] imageBytes = s3Uploader.getObjectBytes(s3ImageUrl);
        return Image.builder()
                    .bytes(SdkBytes.fromByteBuffer(ByteBuffer.wrap(imageBytes)))
                    .build();
    }

    public List<String> getAllImageUrlsFromS3(String directory) {
        return s3Uploader.getAllImageUrlsFromS3(directory);
    }
}

