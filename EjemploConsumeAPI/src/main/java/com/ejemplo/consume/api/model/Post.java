package com.ejemplo.consume.api.model;

import jakarta.json.bind.annotation.JsonbProperty;
import java.io.Serializable;

public class Post implements Serializable {
    @JsonbProperty("userId")
    private Integer userId;
    
    @JsonbProperty("id")
    private Integer id;
    
    @JsonbProperty("title")
    private String title;
    
    @JsonbProperty("body")
    private String body;

    public Post() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
