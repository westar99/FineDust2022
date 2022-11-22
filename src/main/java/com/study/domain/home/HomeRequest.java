package com.study.domain.home;

import lombok.Getter;
import lombok.Setter;

//ku에 생성과 수정에 사용될 클래스

@Getter
@Setter
public class HomeRequest {

    private Long id;             // PK
    private String ku;        // 구이름
    private String title;      // 제목
    private String content;       // 내용
    private String imgAddress;	 // 이미지주소
    private String kakaoAddress;    // 카카오주소
    
}