package com.keepgoing.keepserver.domain.face.service;

import com.keepgoing.keepserver.domain.face.entity.Face;
import com.keepgoing.keepserver.domain.face.repository.FaceRepository;
import com.keepgoing.keepserver.domain.image.service.ImageService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import com.keepgoing.keepserver.global.common.S3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.CompareFacesRequest;
import software.amazon.awssdk.services.rekognition.model.CompareFacesResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FaceRecognitionServiceImpl implements FaceRecognitionService {

    private final ImageService imageService;
    private final RekognitionClient rekognitionClient;
    private final FaceRepository faceRepository;
    private final S3Uploader s3Uploader;

    @Override
    public BaseResponse compareFaces(MultipartFile sourceImage) {
        String tempImageUrl = null;

        try {
            tempImageUrl = s3Uploader.upload(sourceImage, "face-android");

            List<String> studentImageUrls = imageService.getAllImageUrlsFromS3("upload");

            for (String studentImageUrl : studentImageUrls) {
                CompareFacesRequest request = CompareFacesRequest.builder()
                                                                 .sourceImage(imageService.getS3Image(tempImageUrl))  // 임시 저장된 이미지 사용
                                                                 .targetImage(imageService.getS3Image(studentImageUrl))  // 학생 등록 이미지
                                                                 .similarityThreshold(80F)
                                                                 .build();

                CompareFacesResponse response = rekognitionClient.compareFaces(request);

                if (!response.faceMatches().isEmpty()) {
                    Optional<Face> matchedUser = faceRepository.findByS3ImageUrl(studentImageUrl);
                    if (matchedUser.isPresent()) {
                        return new BaseResponse(HttpStatus.OK, "얼굴 인식 성공", matchedUser.get().getEmail());
                    }
                }
            }

            return new BaseResponse(HttpStatus.OK, "얼굴 등록이 되지 않은 이용자입니다.");
        } catch (Exception e) {
            return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, "비교 중 오류가 발생했습니다.", e.getMessage());
        } finally {
            if (tempImageUrl != null) {
                s3Uploader.removeFaceAndroidFile(tempImageUrl);
            }
        }
    }

    @Override
    public BaseResponse registerFace(String email, MultipartFile image) {
        try {
            Optional<Face> existingUser = faceRepository.findByEmail(email);
            if (existingUser.isPresent()) {
                return new BaseResponse(HttpStatus.CONFLICT, "이미 해당 이메일로 등록된 사용자가 있습니다.");
            }

            String s3ImageUrl = s3Uploader.upload(image,"upload");

            Face newUser = new Face();
            newUser.setEmail(email);
            newUser.setS3ImageUrl(s3ImageUrl);
            faceRepository.save(newUser);

            return new BaseResponse(HttpStatus.OK, "얼굴 등록 성공", email);
        } catch (Exception e) {
            return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, "얼굴 등록 실패", e.getMessage());
        }
    }
}
