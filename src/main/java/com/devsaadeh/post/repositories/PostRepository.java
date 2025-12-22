package com.devsaadeh.post.repositories;

import com.devsaadeh.post.models.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {
    @Query("{ $and: [ { 'moment': { $gte: ?1 } }, { 'moment': { $lte: ?2 } }, " +
            "{ $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Post> searchByText(String text, Instant startMoment,Instant endMoment);

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchByTitle(String text);

    List<Post> findByTitleContainingIgnoreCase(String text);
}
