package com.ejemplo.consume.api.bean;

import com.ejemplo.consume.api.model.Post;
import com.ejemplo.consume.api.service.PostService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class PostBean implements Serializable {
    
    @Inject
    private PostService postService;
    
    private List<Post> listaPosts = new ArrayList<>();
    
    @PostConstruct
    public void init() {
        System.out.println("PostBean init");
        cargar();
        System.out.println("PostBean lista size: " + listaPosts.size());
    }
    
    public void cargar() {
        listaPosts = postService.obtenerPosts();
        System.out.println("PostBean cargar size: " + listaPosts.size());
    }
    
    public List<Post> getListaPosts() {
        if (listaPosts == null) {
            listaPosts = new ArrayList<>();
        }
        return listaPosts;
    }
    
    public void setListaPosts(List<Post> listaPosts) {
        this.listaPosts = listaPosts;
    }
}
