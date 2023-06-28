package com.sparta.blogproj.dto;


import lombok.Getter;

@Getter
public class BlogRequestDto {
    private Long id;
    private String username;
    private String contents;
    private String password;
}
