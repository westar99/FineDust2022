package com.study.domain.post;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.study.common.dto.SearchDto;

@Mapper
public interface PostMapper {
	/**
     * 게시글 저장
     * @param params - 게시글 정보
     */
    void save(PostRequest params);

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    PostResponse findById(Long id);
    
    /**
     * 게시글 수정
     * @param params - 게시글 정보
     */
    void update(PostRequest params);

    /**
     * 게시글 삭제
     * @param id - PK
     */
    void deleteById(Long id);

    /**
     * 게시글 리스트 조회
     * * @param params - search conditions
     * @return 게시글 리스트
     */
    List<PostResponse> findAll(SearchDto params);
    
    
    /* 게시글 n개 조회 */
    List<PostResponse> getSomePost(int num);

    /**
     * * @param params - search conditions
     * 게시글 수 카운팅
     * @return 게시글 수
     */
    
    int count(SearchDto params);
    /**
     * * @param params - search conditions
     * 상세글 보기화면 조회수 1증가
     * 
     */
    void viewCount(long id);
    
    /**
     * 홈페이지 리스트 조회
     * @return 게시글 리스트
     */
//    List<PostResponse> findAll();
    
    /**
     * 비밀번호 조회
     * @return
     */
    int getAuth(PostRequest params);

}
