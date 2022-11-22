package com.study.domain.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.common.dto.SearchDto;
import com.study.paging.Pagination;
import com.study.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;
	

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     * @return Generated PK
     */
    @Transactional
    public Long savePost(final PostRequest params) {
        postMapper.save(params);
        return params.getId();
    }

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    public PostResponse findPostById(final Long id) {
    	
    	postMapper.viewCount(id);	//상세조회하면 글조회수 1증가
        return postMapper.findById(id);
    }

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     * @return PK
     */
    @Transactional
    public Long updatePost(final PostRequest params) {
        postMapper.update(params);
        return params.getId();
    }

    /**
     * 게시글 삭제
     * @param id - PK
     * @return PK
     */
    public Long deletePost(final Long id) {
        postMapper.deleteById(id);
        return id;
    }

    /**
     * 게시글 리스트 조회
     * @param params - search conditions
     * @return list & pagination information
     */
    public PagingResponse<PostResponse> findAllPost(final SearchDto params) {
    	System.out.println("서비스의 findAllPost함수. 넘어온 데이터 : "+params);
        int count = postMapper.count(params);
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<PostResponse> list = postMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }
    
    /**
     * 게시글 n개 조회
     * @param params - search conditions
     * @return list & pagination information
     */
    public List<PostResponse> getSomePost(int num) {
        List<PostResponse> list = postMapper.getSomePost(num);
        return list;
    }
    
    
    
    /**
     * 홈페이지 게시글 리스트 조회
     * @return 게시글 리스트
     */
//    public List<PostResponse> findAllPost() {
//        return postMapper.findAll();
//    }
    
    public int getAuth(Long id, String pw) {
    	PostRequest post = new PostRequest();
    	post.setId(id);
    	post.setPassword(pw);
    	return postMapper.getAuth(post);
        
    }

}