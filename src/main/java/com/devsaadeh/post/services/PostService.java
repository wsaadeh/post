package com.devsaadeh.post.services;

import com.devsaadeh.post.models.DTO.PostDTO;
import com.devsaadeh.post.models.entities.Post;
import com.devsaadeh.post.repositories.PostRepository;
import com.devsaadeh.post.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    @Transactional(readOnly = true)
    public PostDTO findById(String id){
        Post post = getPostById(id);
        return new PostDTO(post);
    }

    private Post getPostById(String id){
        Optional<Post> result = repository.findById(id);
        return result.orElseThrow(()-> new ResourceNotFoundException("Object not found"));
    }

}
