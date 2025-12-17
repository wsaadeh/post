package com.devsaadeh.post.models.embedded;

import com.devsaadeh.post.models.entities.User;

public class Author {
    private String id;
    private String name;

    public Author() {
    }

    public Author(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(User entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
