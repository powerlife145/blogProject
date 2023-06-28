package com.sparta.blogproj.repository;

import com.sparta.blogproj.dto.BlogRequestDto;
import com.sparta.blogproj.dto.BlogResponseDto;
import com.sparta.blogproj.entity.Blog;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

}
