package com.study.domain.home;


import lombok.Getter;

//사용자에게 보여줄 데이터를 처리할 응답용 클래스
@Getter
public class HomeResponse {

    private Long id;             // PK
    private String ku;        // 구이름
    private String title;      // 제목
    private String content;       // 내용
    private String imgAddress;	 // 이미지주소
    private String kakaoAddress;    // 카카오주소
}
