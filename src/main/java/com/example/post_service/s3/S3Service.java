package com.example.post_service.s3;


import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.IOException;

/**
 * Date-2/1/2024
 * By Sardor Tokhirov
 * Time-7:47 AM (GMT+5)
 */
@Service
public class S3Service {


    private final S3Client s3;

    public S3Service(S3Client s3) {
        this.s3 = s3;
    }

//    public byte[] getPartialObject(String bucketName, String key, long start, long end) {
//        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .range("bytes=" + start + "-" + end)
//                .build();
//        ResponseInputStream<GetObjectResponse> res = s3.getObject(getObjectRequest);
//        try {
//            return res.readAllBytes();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public byte[] getPartialObject(String bucketName, String key, long start, long end) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .range("bytes=" + start + "-" + end)
                .build();
        ResponseInputStream<GetObjectResponse> res = s3.getObject(getObjectRequest);
        try {
            GetObjectResponse objectResponse = res.response();
            long objectSize = objectResponse.contentLength();
            long rangeSize = end - start + 1;
            if (rangeSize > objectSize) {
                throw new IllegalArgumentException("Specified range exceeds the size of the object");
            }

            return res.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public byte[] getObject(String bucketName, String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        ResponseInputStream<GetObjectResponse> res = s3.getObject(getObjectRequest);
        try {
            return res.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
