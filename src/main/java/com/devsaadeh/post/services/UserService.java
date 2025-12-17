package com.devsaadeh.post.services;

import com.devsaadeh.post.models.DTO.UserDTO;
import com.devsaadeh.post.models.entities.User;
import com.devsaadeh.post.repositories.UserRepository;
import com.devsaadeh.post.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll(){
        List<User> result =  repository.findAll();
        return result.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO findById(String id){
        Optional<User> result = repository.findById(id);
        User user = result.orElseThrow(()-> new ResourceNotFoundException("Object not found"));
        return new UserDTO(user);
    }
}
