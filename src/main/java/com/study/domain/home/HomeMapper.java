package com.study.domain.home;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.study.common.dto.SearchDto;

@Mapper
public interface HomeMapper {

    /**
     * 여행지(ku) 저장(없엘까?)
     * @param params - 게시글 정보
     */
//    void save(HomeRequest params);
	
	 /**
     * 여행지(ku) 각각정보 조회
     * @param id - PK
     * @return 여행지 상세정보
     */
    List<HomeResponse> findByKu(String ku);
	
    /**
     * 여행지(ku) 조회
     * @return 여행지 리스트
     */
//    List<HomeResponse> findAll();

    
    
}
