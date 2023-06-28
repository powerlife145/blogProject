package com.sparta.blogproj.service;

import com.sparta.blogproj.dto.BlogRequestDto;
import com.sparta.blogproj.dto.BlogResponseDto;
import com.sparta.blogproj.entity.Blog;
import com.sparta.blogproj.repository.BlogRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
@Service
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
        return blogRepository.findAll().stream().map(BlogResponseDto::new).toList();        //외우장

    }

    @Transactional
    public Long updateMemo(Long id, BlogRequestDto requestDto) {
        Blog memo = fintMemo(id);
        // 해당 메모가 DB에 존재하는지 확인

            memo.update(requestDto);
            return id;


    }



    public Long deleteMemo(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Blog memo = fintMemo(id);

        blogRepository.delete(memo);
        return id;
    }

    private  Blog fintMemo(Long id){
        return   blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 없습니다.")
        );
    }
}
