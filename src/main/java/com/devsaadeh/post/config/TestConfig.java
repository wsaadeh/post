package com.devsaadeh.post.config;

import com.devsaadeh.post.models.entities.User;
import com.devsaadeh.post.repositories.PostRepository;
import com.devsaadeh.post.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PostRepository postRepository;

//    @Autowired
//    private org.springframework.core.env.Environment env;

    @PostConstruct
    public void init(){
        // Print configuration
//        System.out.println("=== MongoDB Configuration ===");
//        System.out.println("Active profiles: " + Arrays.toString(env.getActiveProfiles()));
//        System.out.println("Database: " + env.getProperty("spring.data.mongodb.database"));
//        System.out.println("Host: " + env.getProperty("spring.data.mongodb.host"));
//        System.out.println("Port: " + env.getProperty("spring.data.mongodb.port"));
//        System.out.println("URI: " + env.getProperty("spring.data.mongodb.uri"));
//        System.out.println("============================");

        repository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null,"Maria Brown","maria@gmail.com");
        User alex = new User(null,"Alex Green","alex@gmail.com");
        User bob = new User(null,"Bob Gray","bob@gmail.com");

        repository.saveAll(Arrays.asList(maria,alex,bob));

    }
}

