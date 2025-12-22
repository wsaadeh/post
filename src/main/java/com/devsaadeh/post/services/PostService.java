package com.devsaadeh.post.services;

import com.devsaadeh.post.models.DTO.PostDTO;
import com.devsaadeh.post.models.entities.Post;
import com.devsaadeh.post.repositories.PostRepository;
import com.devsaadeh.post.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<PostDTO> findByTitle(String text){
        List<Post> list = repository.searchByTitle(text);
        return list.stream().map(PostDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<PostDTO> fullSearch(String text,String start, String end){
        Instant startMoment = convertMoment(start,Instant.ofEpochMilli(0L));
        Instant endMoment = convertMoment(end,Instant.now());
        List<Post> list = repository.searchByText(text,startMoment,endMoment);
        return list.stream().map(PostDTO::new).toList();
    }

    private Instant convertMoment(String moment, Instant instant) {
        try {
            return Instant.parse(moment);
        }catch (DateTimeParseException e){
            return instant;
        }
    }

}
