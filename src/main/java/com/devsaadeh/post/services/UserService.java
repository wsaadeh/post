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
        User user = getUserById(id);
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO insert(UserDTO dto){
        User user = new User();
        copyDtoToEntity(dto,user);
        repository.insert(user);
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO update(String id, UserDTO dto){
        User entity = getUserById(id);
        copyDtoToEntity(dto,entity);
        repository.save(entity);
        return new UserDTO(entity);
    }

    private User getUserById(String id){
        Optional<User> result = repository.findById(id);
        return result.orElseThrow(()-> new ResourceNotFoundException("Object not found"));
    }

    private void copyDtoToEntity(UserDTO dto, User user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
    }
}
