package com.spring.mvc.chap01;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

// 어떤 요청들을 처리할지 공통 URL을 설계
@RequestMapping("/spring/*")
// 이 클래스의 객체를 스프링이 관리하도록 빈을 등록해야함 -> @Component
@Controller // @Component와 같은 개념인데, 핸들러 맵핑 더 잘됨
public class ControllerV1 {

    // 세부요청들은 메서드를 통해 처리
    @RequestMapping("/hello") // http://localhost:8181/spring/hello
    public String hello() {
        System.out.println("\n======== hello 요청이 들어옴! ========");
//        return "/WEB-INF/views/hello.jsp"; // return에다가 어떤 JSP를 열어줄지 '절대'경로를 적음
        return "hello"; // resources -> application.properties에 경로 줄일 수 있음
    }

    // /spring/food 요청이 오면 food.jsp를 열어보세요
    @RequestMapping("/food")
    public String food() { //이름은 안 중요함! food() 이런거 말고 afjjsa() 이렇게 아무거나 만들어도 됨!
        System.out.println("\n======== food 요청이 들어옴! ========");
//        return "/WEB-INF/views/chap01/food.jsp";
        return "chap01/food";
    }


    /*-------------------- 요청 파라미터 읽기 (Query String parameter) -------------------- */
    // == 1. HttpServletRequest 사용하기
    // ==> ex)   /spring/person?name=kim&age=30

    @RequestMapping("/person")
    public String person(HttpServletRequest request) {
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        System.out.println("name = " + name);
        System.out.println("age = " + age);

        return ""; // 일단 jsp파일 없고, 그냥 콘솔로 찍어만 보기
    }


    // == 2. @RequestParam 사용하기
    // ==> ex)  /spring/major?stu=kim&major=business&grade=3
    @RequestMapping("/major")
    public String major(
            String stu, // 이름이 같고, 기본값지정 필요없으면  @RequestParam 생략가능
            @RequestParam("major") String mj, // 만약 파라미터 변수와 중복인 변수가 존재하면 이렇게 사용
            @RequestParam(defaultValue = "1") int grade // 값이 안들어오면 기본값 지정 가능
    ) {
        String major = "전공(파라미터 변수와 중복된 변수가 존재!)";

        System.out.println("stu = " + stu);
        System.out.println("major = " + mj);
        System.out.println("grade = " + grade);

        return "";
    }




}
