package com.sparta.blogproj.dto;

import com.sparta.blogproj.entity.Blog;
import lombok.Getter;

@Getter
public class BlogResponseDto {
    private Long id;
    private String username;
    private String contents;
    private String password;

    public BlogResponseDto(Blog blog) {
        this.id = blog.getId();
        this.username = blog.getUsername();
        this.contents = blog.getContents();
        this.password = blog.getPassword();
//        this.password = blog.getPassword();
//        this.date=blog.getDate();


    }

    public BlogResponseDto(Long id, String username, String contents) {
        this.id = id;
        this.username = username;
        this.contents = contents;
        this.password = password;
    }
}
