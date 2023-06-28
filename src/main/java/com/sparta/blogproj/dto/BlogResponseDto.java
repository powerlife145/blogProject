package com.sparta.blogproj.dto;

import com.sparta.blogproj.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogResponseDto {
    private Long id;
    private String username;
    private String contents;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public BlogResponseDto(Blog blog) {
        this.id = blog.getId();
        this.username = blog.getUsername();
        this.contents = blog.getContents();
        this.password = blog.getPassword();
        this.modifiedAt = blog.getModifiedAt();

    }


}
