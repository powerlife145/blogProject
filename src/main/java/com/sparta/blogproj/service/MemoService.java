package com.sparta.blogproj.service;

import com.sparta.blogproj.dto.BlogRequestDto;
import com.sparta.blogproj.dto.BlogResponseDto;
import com.sparta.blogproj.entity.Blog;
import com.sparta.blogproj.repository.BlogRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MemoService {

    private final BlogRepository blogRepository;

    public MemoService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public BlogResponseDto createMemo(BlogRequestDto requestDto) {
        // RequestDto -> Entity
        Blog memo = new Blog(requestDto);

        Blog saveBlog = blogRepository.save(memo);

        // Entity -> ResponseDto
        BlogResponseDto memoResponseDto = new BlogResponseDto(memo);

        return memoResponseDto;
    }

    public List<BlogResponseDto> getMemos() {
        return blogRepository.findAll();

    }

    public Long updateMemo(Long id, BlogRequestDto requestDto) {
        Blog memo =  blogRepository.findById(id);
        // 해당 메모가 DB에 존재하는지 확인
        if(memo != null) {
            // memo 내용 수정
            blogRepository.update(id, requestDto);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }



    public Long deleteMemo(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Blog memo =  blogRepository.findById(id);
        if(memo != null) {
            blogRepository.delete(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}
