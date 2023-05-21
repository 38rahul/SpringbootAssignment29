package com.springass29.service;

import com.springass29.payload.PostDto;
import com.springass29.payload.PostResponse;

import java.util.List;

public interface PostServie {

    PostDto createPost(PostDto postDto);
    //Step 2 for pagination and Sorting
    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);
}
