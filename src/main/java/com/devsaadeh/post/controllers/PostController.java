package com.devsaadeh.post.controllers;

import com.devsaadeh.post.models.DTO.PostDTO;
import com.devsaadeh.post.models.DTO.UserDTO;
import com.devsaadeh.post.services.PostService;
import com.devsaadeh.post.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable String id){
        PostDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }


}
