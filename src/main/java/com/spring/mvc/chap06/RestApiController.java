package com.spring.mvc.chap06;

import com.spring.mvc.jdbc.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
@RestController // @Controller + @ResponseBody
@RequestMapping("/rests")
public class RestApiController {

    @GetMapping("/hello")
//    @ResponseBody --> 클래스에 넣기
    public String hello() {
        return "안녕하세요!";
    }

    @GetMapping("/foods")
//    @ResponseBody
//    public String[] foods() {
//        String[] foodList = {"탕수육", "족발", "마라탕"};
    public List<String> foods() {
        List<String> foodList = List.of("탕수육", "족발", "마라탕");
        return foodList;
    }


    // 자바객체 -> 제이슨, 제이슨 -> 자바객체 fasterxml.jackson 라이브러리가 바꿔 줌
    @GetMapping("/person")
    public Person person() {
        Person p = new Person(1L, "루피", 3);
        return p;
    }

    @GetMapping("/person-list1")
    public List<Person> personList1() {
        Person p1 = new Person(1L, "루피", 3);
        Person p2 = new Person(2L, "딸긔겅듀", 4);
        Person p3 = new Person(3L, "초코초쿄", 2);
        return List.of(p1, p2, p3);
    }


    @GetMapping("/person-list2")
    public ResponseEntity<?> personList2() {
        Person p1 = new Person(1L, "루피", 3);
        Person p2 = new Person(2L, "딸긔겅듀", 4);
        Person p3 = new Person(3L, "초코초쿄", 2);
        List<Person> personList2 = List.of(p1, p2, p3);

        return ResponseEntity.ok().body(personList2());
    }





    // ResponseEntity<?> 쓰면 상태 응답코드를 정할 수 있음.
    // (정해놓지 않으면, 스프링이 정해둔 것 만 감)
//    @GetMapping("/bmi")
//    public ResponseEntity<?> bmi(
//            @RequestParam(required = false) Double cm,
//            @RequestParam(required = false) Double kg) {
//
//        if (cm == null || kg == null) {
////            return ResponseEntity.badRequest().build();
//            return ResponseEntity.badRequest().body("키랑 몸무게 보내주세융");
//        }
//        double bmi = kg / (cm / 100) * (cm / 100);
//        return ResponseEntity.ok().body(bmi);
//    }


    @GetMapping("/bmi")
    public ResponseEntity<?> bmi(
            @RequestParam(required = false) Double cm,
            @RequestParam(required = false) Double kg) {

        if (cm == null || kg == null) {
            return ResponseEntity.badRequest().body("키랑 몸무게 보내 이새갸");
        }

        double bmi = kg / (cm / 100) * (cm / 100);

        HttpHeaders headers = new HttpHeaders();
        headers.add("fruits", "melon");
        headers.add("hobby", "soccer");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(bmi);
    }







}
