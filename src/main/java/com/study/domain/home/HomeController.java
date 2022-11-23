package com.study.domain.home;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.common.dto.MessageDto;
import com.study.common.dto.SearchDto;
import com.study.domain.post.PostRequest;
import com.study.domain.post.PostResponse;
import com.study.domain.post.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final HomeService homeService;
	private final PostService postService;
	
	
	  //홈화면. 여행지추천은 n개만 반환
	  @GetMapping("/")	  //home/home.do"
	  public String homePage(@ModelAttribute("params") final SearchDto params,Model model) {
		  List<PostResponse> response = postService.getSomePost(4);
		  
		  for (int i = 0; i < response.size(); i++) {
			  PostResponse ele = response.get(i);
			  System.out.println("ele : "+ele);
	      }
		  model.addAttribute("response", response); 
		  return "home/home4";
	  } 
	      
	  
	  
	//여행지(ku) 
	  
	  //어떤구인지 데이터를 파라미터로 같이 전달  예) /home/ku.do?ku=양천구;  아니면 숫자
	  @GetMapping("/home/ku.do") 
	  public String openKu(@RequestParam final String ku,Model model) {
		  List<HomeResponse> mustSee = homeService.findHomeByKu(ku);
			/*
			 * System.out.println("---컨트롤러에서 화면에 전달할 구의 정보---"); for (int i = 0; i <
			 * home.size(); i++) { System.out.println(home.get(i)); }
			 */
		  model.addAttribute("mustSee", mustSee); 
//		  String ku="영등포구",;
//				 title="타임스퀘어",
//				 content="쇼핑하고 즐겨요",
//				 imgAddress="img/tourPicture/street7.png",
//				 kakaoAddress="11411449";
		    //해당구의 상세정보를 가져오는 db를 조회하는 서비스 호출
		    //쿼리의 결과값을 model에 넣기.  밑의 1번과 똑같은거
		    //home4.html에 있는 타이틀이 db컬럼에 지정되어있고 where조건으로 그걸 걸어서 리스트를 받아오면됨
		    
//		    model.addAttribute("ku",ku );
//		    model.addAttribute("title",title ); 
//		    model.addAttribute("content", content); 
//		    model.addAttribute("imgAddress",imgAddress); 
//		    model.addAttribute("kakaoAddress",kakaoAddress); 
		    //1. 여러스트링들을 담을 객체 하나를 만들어서 객체 하나만 화면으로 전달
		     //   현재상태를 위로 바꾼 테스트가 끝나면 2번
		    //2. 객체 배열을 화면으로 전달
		    	//타임리프의 foreach를 써서 탭만들기
		    	//하나의 탭 만드는건 1번에서 했기 때문에
		    return "home/ku"; 
	  }

	 
}
