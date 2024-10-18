package com.keepgoing.keepserver.global.common.S3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
@Service
public class S3Uploader {

    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // MultipartFile을 전달받아 File로 전환한 후 S3에 업로드
    public String upload(MultipartFile multipartFile, String dirName) throws IOException {

        File uploadFile = convert(multipartFile);
        return upload(uploadFile, dirName);
    }

    private String upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);  // 로컬에 생성된 File 삭제 (MultipartFile -> File 전환 하며 로컬에 파일 생성됨)
        return uploadImageUrl;      // 업로드된 파일의 S3 URL 주소 반환
    }


    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, uploadFile)
        );
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    public void removeFaceAndroidFile(String s3ImageUrl) {
        String key = extractKeyFromUrl(s3ImageUrl);
        if (key.startsWith("face-android/")) {
            amazonS3Client.deleteObject(bucket, key);
            log.info("파일이 삭제되었습니다 " + s3ImageUrl);
        } else {
            log.warn("버킷에 없는 파일입니다 " + s3ImageUrl);
        }
    }

    private String extractKeyFromUrl(String s3ImageUrl) {
        String decodedUrl = URLDecoder.decode(s3ImageUrl, StandardCharsets.UTF_8);
        return decodedUrl.split(".com/")[1];  // uploads/ 이후의 경로만 추출
    }

    public List<String> getAllImageUrlsFromS3(String directory) {
        List<String> imageUrls = new ArrayList<>();
        ListObjectsV2Request request = new ListObjectsV2Request()
                .withBucketName(bucket)
                .withPrefix(directory);

        ListObjectsV2Result result = amazonS3Client.listObjectsV2(request);

        result.getObjectSummaries().forEach(s3Object -> {
            String imageUrl = amazonS3Client.getUrl(bucket, s3Object.getKey()).toString();
            imageUrls.add(imageUrl);
        });

        return imageUrls;
    }

    // S3에서 이미지 데이터를 바이트 배열로 가져오기
    public byte[] getObjectBytes(String s3ImageUrl) throws IOException {
        String key = extractKeyFromUrl(s3ImageUrl);  // URL에서 키 추출
        try (InputStream inputStream = amazonS3Client.getObject(new GetObjectRequest(bucket, key)).getObjectContent()) {
            return inputStream.readAllBytes();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new IOException("S3 객체 파일 변환 실패", e);
        }
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        return file;
    }

}