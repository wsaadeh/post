package com.devsaadeh.post.controllers;

import com.devsaadeh.post.models.DTO.UserDTO;
import com.devsaadeh.post.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> dto = service.findAll();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        UserDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id,@RequestBody UserDTO dto){
        dto = service.update(id,dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
