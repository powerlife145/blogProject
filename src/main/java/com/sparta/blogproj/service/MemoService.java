package com.sparta.blogproj.service;

import com.sparta.blogproj.dto.BlogRequestDto;
import com.sparta.blogproj.dto.BlogResponseDto;
import com.sparta.blogproj.entity.Blog;
import com.sparta.blogproj.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
        BlogResponseDto memoResponseDto = new BlogResponseDto(saveBlog);

        return memoResponseDto;
    }

    public List<BlogResponseDto> getMemos() {
        return blogRepository.findAllByOrderByModifiedAtDesc().stream().map(BlogResponseDto::new).toList();        //외우장

    }

    @Transactional
    public Long updateMemo(Long id, BlogRequestDto requestDto) {
        Blog memo = findMemo(id);
        if(requestDto.getPassword().equals(memo.getPassword())){
            memo.update(requestDto);
        }else{throw new IllegalArgumentException("비번 틀림");}
        return id;

    }

    @Transactional
    public Long deleteMemo(Long id, String password) {
        Blog memo = findMemo(id);
        // 로그 출력으로 실제 값 확인
        System.out.println("Input password: " + password);
        System.out.println("Stored password: " + memo.getPassword());

        // 해당 메모가 DB에 존재하는지 확인
        if(memo.getPassword().equals(password)){
            blogRepository.delete(memo);
        } else {
            throw new IllegalArgumentException("비번 불일치");
        }
        return id;

    }



    public Blog findMemo(Long id){
        return   blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 없습니다.")
        );
    }
}
