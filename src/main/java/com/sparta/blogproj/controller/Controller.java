package com.sparta.blogproj.controller;

import com.sparta.blogproj.dto.BlogRequestDto;
import com.sparta.blogproj.dto.BlogResponseDto;
import com.sparta.blogproj.entity.Blog;
import com.sparta.blogproj.service.MemoService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

// controller -> service -> repository 강한 결합이라 약하게 만들어야 함.

@RestController
@RequestMapping("/api")
public class Controller {

    private final MemoService memoService;

    public Controller(MemoService memoService) {
        this.memoService = memoService;
    }


    @PostMapping("/memos")
    public BlogResponseDto createMemo(@RequestBody BlogRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }

    @GetMapping("/memos")
    public List<BlogResponseDto> getMemos() {
        return memoService.getMemos();
        // DB 조회

    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        return memoService.updateMemo(id, requestDto);
    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        return memoService.deleteMemo(id);


    }


}