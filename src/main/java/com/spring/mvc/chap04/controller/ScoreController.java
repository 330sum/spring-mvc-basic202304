package com.spring.mvc.chap04.controller;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import com.spring.mvc.chap04.repository.ScoreRepositoryImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
 *   << 요청 URL >>
 *   1. 학생 성적 정보 등록화면을 보여주고, 성적정보 목록조회 처리
 *       - /score/list : GET (조회니까 get방식!)
 *
 *   2. 성적 정보 등록 처리 요청
 *       - /score/register : POST (등록(갱신)되니까 post방식!)
 *
 *   3. 성적정보 삭제 요청
 *       - /score/remove : POST (갱신이니까 post)
 *
 *   4. 성적정보 상세 조회 요청
 *       - /score/detail : GET (조회니까 get)
 * */
@Controller
@RequestMapping("/score")
//@AllArgsConstructor : 모든 필드를 초기화하는 생성자
@RequiredArgsConstructor // : final 필드만 초기화하는 생성자
public class ScoreController {

    // 저장소에 의존해야 데이터를 받아서 클라이언트에게 응답할 수 있음
    // 구현체에 의존하면 안됨! DIP위반! 역할에 의존하도록 만들기 (인터플레이스)
    // 객체 바로 주입하면 안되고 DI! 생성자 주입!
    private final ScoreRepository repository;


    // 생성자 주입객체의 불변성을 위해서 final 붙이기! (계속 메모리에 저장, 중간에 안바뀌게)
    // 세터없으니 중간에 못 들어옴
    // 스프링한테 객체 구현을 맡기기 (=빈 등록하기!) -> @Component -> ScoreRepositoryImpl로 가서 붙이기
    // 의존 객체 주입 해달라! -> @Autowired
    // 근데 @Autowired이거 생략해도 돌아감! 왜? 자동@Autowired 조건이 있음
    // -> 만약에 클래스의 생성자가 단 1개라면 자동으로 @Autowired를 써줌 (생성자가 여러개인 경우는 내가 직접 골라서 @Autowired 붙여야 함!)
    // 생성자를 만들어주는 롬복기능 -> AllArgs하면 그냥 자동으로 주입 됨! (단 noArgs 넣으면 안됨- 생성자가 2개가 되니까)
//    @Autowired
//    public ScoreController(ScoreRepository repository) {
//        this.repository = repository;
//    }

    /*□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□*/

    // 1. 성적등록화면 띄우기 + 정보목록조회
    @GetMapping("/list")
    public String list(Model model) {
        System.out.println("/score/list : GET!");

        List<Score> scoreList = repository.findAll();
        model.addAttribute("sList", scoreList);

        return "chap04/score-list";
    }

    /*■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■*/


    // 2. 성적정보 등록 처리
    @PostMapping("/register")
    public String register(ScoreRequestDTO dto) {
        System.out.println("/score/register : POST!");

        // 2-1. 입력데이터(쿼리스트링) 읽기
        System.out.println("dto" + dto);

        // 2-2. dto(ScoreDTO)를 entity(Score)로 변환해야 함! (-> Score생성자에게 dto 넘기기)
        Score score = new Score(dto);

        // 2-3. save명령
        repository.save(score);

        // 2-4. redirect(리다이렉트) : 자동으로 새로운 요청을 보내는 것
        /*
          return "chap04/score-list"; - 뷰포워딩(JSP파일)

             등록요청에서 JSP 뷰포워딩을 하면, 갱신된 목록을 다시 한번 저장소에서 불러와서
             모델에 담는 추가적인 코드가 필요하지만, (63번~64번라인)

             리다이렉트를 통해서 위어서 만든 /score/list : GET을 (59번라인)
             자동으로 다시 보낼 수 있다면 번거로운 코드가 줄어 들 수 있음.
        */
        //   JSP 파일을 적는 것이 아니라 (return "chap04/score-list";) - 뷰포워딩
        //   redirect: 요청URL 작성 (return "redirect:/http://localhost:8181/score/list";) - 리다이렉트

        return "redirect:/score/list";
    }

    /*■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■*/

    // 3. 성적정보 삭제
    @GetMapping("/remove")
    public String remove(@RequestParam int stuNum) {
        System.out.println("/score/remove : GET (원래는 POST! get으로 하면 안되는데, 너무 어려우니까)");

        repository.deleteByStuNum(stuNum);

        return "redirect:/score/list";
    }

    /*■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■*/


    // 4. 상세조회
    @GetMapping("/detail")
    public String detail() {
        System.out.println("/score/detail : GET");
        return "";
    }

}
