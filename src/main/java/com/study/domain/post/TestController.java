package com.study.domain.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RestController는 클래스 레벨에 선언할 수 있으며,붙은 컨트롤러의 모든 메서드는 자동으로 @ResponseBody가 적용
public class TestController {

    @GetMapping("/members")
    //@ResponseBody // public @ResponseBody List<Map<String, Object>> findAllMember()와 같이 리턴 타입 앞에 선언 가능
    //@Controller는 리턴뒤에 jsp를 붙여주지만  @ResponseBody로 그냥 맴버즈를 내보냄
    public List<Map<String, Object>> findAllMember() {
        List<Map<String, Object>> members = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Map<String, Object> member = new HashMap<>();
            member.put("id", i);
            member.put("name", i + "번 개발자");
            member.put("age", 10 + i);
            members.add(member);
        }
        return members;
    }

}