package com.mannan.demoapp.Repository.Interfaces;

import com.mannan.demoapp.Model.PostPackage.Post;

import java.sql.SQLException;
import java.util.List;

public interface IPostAzure {
    boolean create(Post post) throws SQLException;
    Post getPostById(Long id) throws SQLException;
    List<Post> getAccountPostsByPcn(Long pcn) throws SQLException;
    boolean delete(Long id) throws SQLException;
    List<Post> getAccountNewsfeed(Long id) throws SQLException;
}
