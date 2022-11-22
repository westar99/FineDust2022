package com.study;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.domain.post.PostRequest;
import com.study.domain.post.PostService;

@SpringBootTest
public class PostServiceTest {

	@Autowired
    PostService postService;

    @Test
    void save() {
        PostRequest params = new PostRequest();
        params.setTitle("1번 게시글 Junit  Service 테스트 제목");
        params.setContent("1번 게시글 내용");
        params.setWriter("테스터");
        params.setNoticeYn(false);
        Long newPostId = postService.savePost(params);
        System.out.println("생성된 게시글 ID : "+newPostId);
    }
}
