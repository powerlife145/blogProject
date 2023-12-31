package com.sparta.blogproj.entity;

import com.sparta.blogproj.dto.BlogRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "memo") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Blog extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;
    @Column(name = "password", nullable = false, length = 500)
    private String password;
    @Column(name = "title", nullable = false, length = 500)
    private String title;


    public Blog(BlogRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
    }

    public void update(BlogRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.title =requestDto.getTitle();

    }
}