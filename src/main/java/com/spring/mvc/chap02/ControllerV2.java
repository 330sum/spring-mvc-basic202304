package com.spring.mvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/* ------- 응답 데이터 처리 ------ */
@RequestMapping("/model/*")
@Controller // 스프링 컨트롤러(컴포넌트)에 빈으로 등록
public class ControllerV2 {

    // /model/hobbies : GET
    // hobbies.jsp파일로 이름정보와 취미목록 정보를 보내주고 싶을 때

    // == 1. Model객체를 이용
    @GetMapping("/hobbies")
    public String hobbies(Model model) {

        String name = "멍멍이";
        List<String> hobbies = List.of("산책", " 밥먹기", "낮잠자기");
        // jsp로 보낼 데이터를 모델에 담는다
        model.addAttribute("n", name);
        model.addAttribute("hList", hobbies);


//        /WEB-INF/view/chap02/hobbies.jsp
        return "chap02/hobbies";
    }


    // == 2. ModelAndView 사용하기
    // /model/hobbies2 : GET
    // hobbies.jsp를 포워딩

    // ModelAndView는 리턴 타입을 ModleAndView로 설정
    @GetMapping("/hobbies2")
    public ModelAndView hobbies2() {

        // jsp로 보내야 할 데이터 -> 모델에 담고
        String name = "짹짹이";
        List<String> hList = List.of("전깃줄에서 졸기", "조잘대기");

        // 포워딩할 뷰의 이름 -> 뷰에 담기
        String viewName = "chap02/hobbies";


        //ModelAndView 객체 만들고 위 2개의 데이터 담기
        ModelAndView mv = new ModelAndView();
        mv.setViewName(viewName);
        mv.addObject("n", name);
        mv.addObject("hList", hList);


        return mv;
    }

}
