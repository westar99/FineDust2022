package com.study.domain.post;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.study.common.dto.MessageDto;
import com.study.common.dto.SearchDto;
import com.study.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {

	// @Autowired 가 없음->@RequiredArgsConstructor가 final롤 선언된 모든 맴버의 생성자를 만듬
    private final PostService postService;

    // 게시글 작성 페이지
    @GetMapping("/post/write.do")//받음:겟메핑에 접속할 Url주소를 입력
    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
        if (id != null) {//null이 아니면 글수정 글의 데이터를 모델에 담음
            PostResponse post = postService.findPostById(id);
            model.addAttribute("post", post);
        }
        //null이면 
        return "post/write";//templtes/post/write.html을 부라우저로 전송!
    }
    // 신규 게시글 생성
    @PostMapping("/post/save.do")//던짐
    public String savePost(final PostRequest params, Model model) {
        postService.savePost(params);
        //return "redirect:/post/list.do";
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }
    // 게시글 리스트 페이지
    @GetMapping("/post/list.do")
    public String openPostList(@ModelAttribute("params") final SearchDto params, Model model) {
    	System.out.println("포스트의 리스트 컨트롤러. /post/list.do 인식됨");
    	PagingResponse<PostResponse> response = postService.findAllPost(params);
    	System.out.println("포스트의 리스트 컨트롤러. 서비스로부터 받아온 결과 : "+response);
        model.addAttribute("response", response);
        //model.addAttribute("posts", param);없어도 @ModelAttribute로 가능
        
        return "post/list";
    }
    
    // 게시글 상세 페이지
    @GetMapping("/post/view.do")
    public String openPostView(@RequestParam final Long id, Model model) {
        PostResponse post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "post/view";
    }
    // 기존 게시글 수정
    @PostMapping("/post/update.do")
    public String updatePost( PostRequest params, 
    		@RequestParam final Map<String, Object> queryParams, Model model) {
    	
    	MessageDto message = null;
    	Long postId =  Long.parseLong((String) queryParams.get("id"));	//Object를 String으로 바꿨다가 Long으로 변경
    	String password = (String)queryParams.get("password");	//Object를 String으로 변경
    	int isAuth = postService.getAuth(postId, password);
    	
    	if(isAuth==1) {
    		postService.updatePost(params);
            message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list.do", RequestMethod.GET, queryParams);
    	}else {
    		message = new MessageDto("비밀번호가 틀려 수정하지 못했습니다.", "/post/list.do", RequestMethod.GET, queryParams);
    	}
        
        return showMessageAndRedirect(message, model);
    }
    
    // 게시글 삭제(비밀번호추가)
    @PostMapping("/post/delete.do")
    public String deletePost(@RequestParam final Map<String, Object> queryParams, Model model) {
    	MessageDto message = null;
    	Long postId =  Long.parseLong((String) queryParams.get("id"));	//Object를 String으로 바꿨다가 Long으로 변경
    	String password = (String)queryParams.get("password");	//Object를 String으로 변경
    	int isAuth = postService.getAuth(postId, password);
    	
		if(isAuth==1) {
			postService.deletePost(postId);
			message = new MessageDto("게시글 삭제가 완료되었습니다.","/post/list.do",RequestMethod.GET,queryParams);
		}
		else { 
			message = new MessageDto("비밀번호가 틀려 삭제불가합니다.","/post/list.do",RequestMethod.GET,queryParams);
		}
		 
        return showMessageAndRedirect(message, model);
    }
    // 게시글 삭제(원본)
//    @PostMapping("/post/delete.do")
//    public String deletePost(@RequestParam final Long id,
//            @RequestParam final Map<String, Object> queryParams,
//            Model model) {
//        postService.deletePost(id);
//        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list.do", RequestMethod.GET,queryParams);
//        return showMessageAndRedirect(message, model);
//    }
    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }
		


  
		  
}
