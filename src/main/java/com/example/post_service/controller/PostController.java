package com.example.post_service.controller;

import com.example.post_service.model.ClassPost;
import com.example.post_service.service.ClassPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.UUID;

/**
 * Date-2/27/2024
 * By Sardor Tokhirov
 * Time-4:13 AM (GMT+5)
 */

@RestController
@RequestMapping("api/v1/post")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostController {
    private final ClassPostService classPostService;

    public PostController(ClassPostService classPostService) {
        this.classPostService = classPostService;
    }


    @GetMapping(value = "/image/{uuid}")
    public ResponseEntity<String> getImage(@PathVariable String uuid) {
        byte[] imageBytes = classPostService.getPostImage(uuid);
        return ResponseEntity.ok(Base64.getEncoder().encodeToString(imageBytes));
    }



    @GetMapping("/{uuid}")
    public ResponseEntity<ClassPost> getPost(@PathVariable UUID uuid) {
        return  ResponseEntity.ok(classPostService.getClassPost(uuid));
    }

}
