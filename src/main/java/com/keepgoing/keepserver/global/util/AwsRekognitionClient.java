package com.keepgoing.keepserver.global.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsRekognitionClient {

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKeyId;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public RekognitionClient rekognitionClient() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretKey);
        return RekognitionClient.builder()
                                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                                .region(Region.of(region))
                                .build();
    }

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretKey);
        return S3Client.builder()
                       .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                       .region(Region.of(region))
                       .build();
    }
}
