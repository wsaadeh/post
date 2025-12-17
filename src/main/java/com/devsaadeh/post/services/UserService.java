package com.devsaadeh.post.services;

import com.devsaadeh.post.models.DTO.UserDTO;
import com.devsaadeh.post.models.entities.User;
import com.devsaadeh.post.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<UserDTO> findAll(){
        List<User> result =  repository.findAll();
        return result.stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
