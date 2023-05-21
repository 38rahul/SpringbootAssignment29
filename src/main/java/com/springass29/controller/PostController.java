package com.springass29.controller;

import com.springass29.entity.Post;
import com.springass29.payload.PostDto;
import com.springass29.payload.PostResponse;
import com.springass29.service.PostServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("SpringBoot29/api/posts")
public class PostController {
    @Autowired
    private PostServie postServie;

    // create blog post

    @PostMapping()
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
       return new ResponseEntity<>( postServie.createPost(postDto), HttpStatus.CREATED );
    }

    @GetMapping()
    public PostResponse getAllPost(
            // Step1
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false)  String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        return  postServie.getAllPosts(pageNo, pageSize,sortBy,sortDir);
    }


}
