package com.springass29.service.impl;

import com.springass29.entity.Post;
import com.springass29.payload.PostDto;
import com.springass29.payload.PostResponse;
import com.springass29.repository.PostRepository;
import com.springass29.service.PostServie;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import   org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostServie {

    @Autowired
   private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto) {
        // convert DTO to entity
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        // Convert ENTITY to DTO
        PostDto postResponse =  mapToDto(newPost);
        return postResponse;

    }

    @Override
    public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Create pageable instance. Step 2

        //This line is when sorting was not implemented
        //Pageable pageable = (Pageable) PageRequest.of(pageNo,pageSize);

        //This line is when sorting is also  implemented

       // Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts =  postRepository.findAll(pageable);

        //get content from page Object
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content= listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return  postResponse;
    }

    // convert post Entity into PostDto
    private PostDto mapToDto(Post post){
        PostDto postDto = modelMapper.map(post,PostDto.class);
        return postDto;
    }
    //Convert Dto to Entity
    private Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto,Post.class);
        return post;
    }

}
