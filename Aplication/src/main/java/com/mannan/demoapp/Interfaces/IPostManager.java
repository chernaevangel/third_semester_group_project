package com.mannan.demoapp.Interfaces;

import com.mannan.demoapp.Model.PostPackage.Post;

import java.util.List;

public interface IPostManager {
    boolean createPost(Post post);
    Post getPost(Long id);
    List<Post> getAccountPosts(Long pcn);
    boolean deletePost(Long id);
    List<Post> getNewsFeed(Long pcn);
}
