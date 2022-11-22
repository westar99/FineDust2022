package com.study.domain.home;


import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeService {

	@Autowired
    private final HomeMapper homeMapper;
	
    /**
     * 여행지(ku) 저장- 사용여부 결정필요
     * @param params - 여행지 정보
     * @return Generated PK
     */
//    @Transactional
//    public Long saveHome(final HomeRequest params) {
//        homeMapper.save(params);
//        return params.getId();
//    }

    /**
     * 여행지 각각의 정보 조회
     * @param id - PK
     * @return 여행지 상세정보
     */
    public List<HomeResponse> findHomeByKu(final String ku) {
        return homeMapper.findByKu(ku);
    }
    /**
     * 여행지 전체 조회
     * @return 여행지 리스트
     */
//    public List<HomeResponse> findAllHome() {
//        return homeMapper.findAll();
//    }

}