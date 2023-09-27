package com.myblog7.controller;

import com.myblog7.entity.Post;
import com.myblog7.payload.PostDto;
import com.myblog7.payload.PostResponse;
import com.myblog7.service.PostService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")//it mean this method can be accessed by only admin this is called as autharization
    @PostMapping  //http://localhost:8080/api/post
    public ResponseEntity<?> savePost(@Valid @RequestBody PostDto postDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto=postService.savePost(postDto);//know this dto then given to service layer

        return new ResponseEntity<>(dto, HttpStatus.CREATED);//201
    }
    @DeleteMapping("/{id}")//http://localhost:8080/api/post/id no.
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post is deleted",HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")//http://localhost:8080/api/post/id no.
    public ResponseEntity<PostDto> update(@PathVariable("id")long id, @RequestBody PostDto postDto){
        PostDto dto = postService.updatePost(id, postDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}")//getting  data from the db by id
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //localhost:8080/api/post/pageNo=0&pagesize=5&sortBy=title&sortDir=desc
    @GetMapping
    public PostResponse getPosts(
            @RequestParam(value ="pageNo",defaultValue = "0",required=false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = "5",required=false) int pageSize,
            @RequestParam(value ="sortBy",defaultValue = "id",required =false)String sortBy,
            @RequestParam(value ="sortDir",defaultValue = "asc",required =false)String sortDir


    ){
        PostResponse postResponse = postService.getPosts(pageNo,pageSize,sortBy,sortDir);
        return postResponse;
    }
}