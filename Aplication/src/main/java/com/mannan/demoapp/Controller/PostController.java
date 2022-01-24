package com.mannan.demoapp.Controller;

import com.mannan.demoapp.Interfaces.IPostManager;
import com.mannan.demoapp.Model.PostPackage.Post;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/story")
@AllArgsConstructor
public class PostController {

    private IPostManager postManager;

    //region Post REST API Methods
    @GetMapping("{id}")
    public ResponseEntity<Post> getPosts(@PathVariable Long id) {
        Post post = postManager.getPost(id);
        if (post != null) {return ResponseEntity.ok().body(post);}
        else {return ResponseEntity.notFound().build();}
    }

    @GetMapping("account/{pcn}")
    public ResponseEntity<List<Post>> getPostsByPcn(@PathVariable Long pcn) {
        List<Post> posts = postManager.getAccountPosts(pcn);

        if (posts != null) {return ResponseEntity.ok().body(posts);}
        else {return ResponseEntity.notFound().build();}
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        postManager.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        if(postManager.createPost(post)) {
            return ResponseEntity.ok().build();
        }
        else {return ResponseEntity.notFound().build();}


    }
    //endregion

    //region NewsFeed
    @GetMapping("/feed/{pcn}")
    public ResponseEntity<List<Post>> getAccountNewsFeed(@PathVariable Long pcn) {
        List<Post> newsFeed = postManager.getNewsFeed(pcn);
        if(newsFeed == null) {
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok().body(newsFeed);
    }

    //endregion

}
