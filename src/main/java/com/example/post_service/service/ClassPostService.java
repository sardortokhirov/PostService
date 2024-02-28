package com.example.post_service.service;

import com.example.post_service.model.ClassPost;
import com.example.post_service.repository.ClassPostRepository;
import com.example.post_service.s3.S3Buckets;
import com.example.post_service.s3.S3Service;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Date-2/27/2024
 * By Sardor Tokhirov
 * Time-4:16 AM (GMT+5)
 */
@Service
public class ClassPostService {
    private final S3Service s3Service;
    private final S3Buckets s3Buckets;



    private final ClassPostRepository classPostRepository;

    public ClassPostService(S3Service s3Service, S3Buckets s3Buckets, ClassPostRepository classPostRepository) {
        this.s3Service = s3Service;
        this.s3Buckets = s3Buckets;
        this.classPostRepository = classPostRepository;
    }


    public ClassPost getClassPost(UUID uuid) {
        ClassPost classPost = classPostRepository.findById(uuid).orElseThrow();
        return classPost;
    }

    public byte[] getPostImage(String uuid) {
        if ( uuid == null) return null;

        return s3Service.getObject(
                s3Buckets.getPostImage(),
                "%s".formatted( uuid)
        );
    }

}
