package com.devsaadeh.post.config;

import com.devsaadeh.post.models.embedded.Author;
import com.devsaadeh.post.models.embedded.Comment;
import com.devsaadeh.post.models.entities.Post;
import com.devsaadeh.post.models.entities.User;
import com.devsaadeh.post.repositories.PostRepository;
import com.devsaadeh.post.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
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
        User bob = new User(null,"Bob Grey","bob@gmail.com");

        repository.saveAll(Arrays.asList(maria,alex,bob));

        Post post1 = new Post(null, Instant.parse("2025-12-13T11:15:01Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new Author(maria));
        Post post2 = new Post(null, Instant.parse("2025-12-14T10:05:49Z"), "Bom dia", "Acordei feliz hoje!", new Author(maria));

        Comment c1 = new Comment("Boa viagem mano!", Instant.parse("2025-12-13T14:30:01Z"), new Author(alex));
        Comment c2 = new Comment("Aproveite", Instant.parse("2025-12-13T15:38:05Z"), new Author(bob));
        Comment c3 = new Comment("Tenha um ótimo dia!", Instant.parse("2025-12-14T12:34:26Z"), new Author(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        repository.save(maria);

    }
}

