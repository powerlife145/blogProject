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
        Blog memo = fintMemo(id);
        // 해당 메모가 DB에 존재하는지 확인
        // 비번과 같다면 리턴
        if(!checkPassword(id, requestDto.getPassword())){
            memo.update(requestDto);
            
        }
        return id;
    }



    public Long deleteMemo(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Blog memo = fintMemo(id);
        blogRepository.delete(memo);
        return id;
    }

    public boolean checkPassword(Long id, @RequestBody String password){
        Blog memo = fintMemo(id);
        return  memo.getPassword().equals(password);
    }


    private  Blog fintMemo(Long id){
        return   blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 없습니다.")
        );
    }
}
