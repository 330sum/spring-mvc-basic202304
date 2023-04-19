package com.spring.mvc.chap01;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    // == 3. 커맨드 객체 이용하기 (가장 많이 사용)
    // == 쿼리 스트링의 양이 너무 많을 경우, 또는 연관성이 있을 경우
    // ==> ex)  /spring/order?oNum=20230419007-P&goods=구두&amount=3&price=50000...
    // - DTO : 데이터를 감싸주는 역할 (객체자동포장)

    @RequestMapping("/order")
    public String order(OrderRequestDTO dto) {
        System.out.println("dto = " + dto);
        return "";
    }


    // == 4. URL에 경로로 붙어 있는 데이터 읽기 (?물음표 안쓰고 /슬래쉬 쓰는 '요즘 뜨고있는 버전')
    // ==> /spring/member/hong/107
    //      hong이라는 유저의 107번 게시글을 읽고싶음
    @RequestMapping("/member/{userName}/{bNo}")
    public String member(
            @PathVariable String userName,
            @PathVariable long bNo
            // @PathVariable(/에 사용) 생략하면 안됨!
            // 생략하면 RequestParam(?에 사용)으로 읽게 됨
    ) {
        System.out.println("userName = " + userName);
        System.out.println("bNo = " + bNo);
        return "";
    }


    /*
      1. @GetMapping
        GET방식 (데이터 조회시 사용): URL 길이 한정, 보안위험, 캐싱
      2. @PostMapping
        POST방식 (데이터 변동시 사용): 크기 제한없고, URL데이터 노출 없음. 캐싱 안함.
    */


    // 음식 선택 요청 처리
    // POST방식으로 안주면 안받을꺼얌
//    @RequestMapping(value = "/food-select", method = RequestMethod.POST)
    @PostMapping("/food-select")
    public String foodSelect(String foodName, String category) {
        System.out.println("foodName = " + foodName);
        System.out.println("category = " + category);
        return "hello";
    }


}
