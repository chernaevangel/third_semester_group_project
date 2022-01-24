package com.mannan.demoapp.Manager;

import com.mannan.demoapp.Interfaces.IPostManager;
import com.mannan.demoapp.Model.PostPackage.Post;
import com.mannan.demoapp.Repository.Interfaces.IPostAzure;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PostManager implements IPostManager {

    IPostAzure dataClass;

    public PostManager(IPostAzure dataClass) {this.dataClass = dataClass;}

    @Override
    public boolean createPost(Post post) {
        try {
            return dataClass.create(post);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public Post getPost(Long id) {
        try {
            return dataClass.getPostById(id);
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
    }

    @Override
    public List<Post> getAccountPosts(Long pcn) {
        try {
            List<Post> posts = dataClass.getAccountPostsByPcn(pcn);
            Collections.reverse(posts);
            return posts;
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
    }

    @Override
    public boolean deletePost(Long id) {
        try {
            return dataClass.delete(id);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public List<Post> getNewsFeed(Long pcn) {
        try {
            return dataClass.getAccountNewsfeed(pcn);
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
    }
}
