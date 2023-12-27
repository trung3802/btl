package com.example.sinhnhat.service;

import java.util.List;

import com.example.sinhnhat.entity.Blog;
import com.example.sinhnhat.model.request.CreateBlogRequest;

public interface BlogService {
    
    List<Blog> getList();

    List<Blog> getListNewest(int limit);

    Blog getBlog(long id);

    Blog createBlog(CreateBlogRequest request);

    Blog updateBlog(long id,CreateBlogRequest request);

    void deleteBlog(long id);

}
