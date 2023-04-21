package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

// Impl : 구현체가 하나인 경우, 이렇게 붙임
@Repository // 스프링 빈 등록(Component): 객체의 생성 제어권을 스프링에게 위임
public class ScoreRepositoryImpl implements ScoreRepository {

    // 성적정보를 쌓아놓을 곳 (Map사용 -> 순서대로가 아니기 때문)
    // key: 학번, value: 성적정보
    private static final Map<Integer, Score> scoreMap; // 메모리에 하나만 존재하도록(static), 그리고 못바뀌게(final), 내부로 바로 접근 못하게(private)

    // 학번에 사용할 일련번호
    private static int sequence; // 학생 한명 마다 붙이니까 final 쓰면 안됨 (초기화 하지 않은 건? 클래스 생성 시 다 0 또는 null로 초기화 되기 때문)


    static {
        scoreMap = new HashMap<>();

        // 기본데이터
        Score stu1 = new Score(new ScoreRequestDTO("뽀로로", 100, 50, 70));
        Score stu2 = new Score(new ScoreRequestDTO("루피", 33, 56, 12));
        Score stu3 = new Score(new ScoreRequestDTO("크롱", 88, 12, 0));

        stu1.setStuNum(++sequence);
        stu2.setStuNum(++sequence);
        stu3.setStuNum(++sequence);

        scoreMap.put(stu1.getStuNum(), stu1);
        scoreMap.put(stu2.getStuNum(), stu2);
        scoreMap.put(stu3.getStuNum(), stu3);
    }

    /*□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□*/

    @Override
    public List<Score> findAll() {
        //정렬해서 주기!
        return new ArrayList<>(scoreMap.values())
                .stream()
                .sorted(Comparator.comparing(score -> score.getStuNum()))
                .collect(Collectors.toList())
                ;
        // new ArrayList<> 지워도 됨. 왜? toList()가 있어서 -> ArrayList 객체가 생성 됨
    }

    public List<Score> findAll(String sort) {
        Comparator<Score> comparator = Comparator.comparing(score -> score.getStuNum());
        switch (sort) {
            case "num":
                comparator = Comparator.comparing(Score::getStuNum);
                break;
            case "name":
                comparator = Comparator.comparing(Score::getName);
                break;
            case "avg":
                comparator = Comparator.comparing(Score::getAverage);
                break;
        }
        return scoreMap.values()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList())
                ;
    }

    /*■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■*/

    @Override
    public boolean save(Score score) {
        if (scoreMap.containsKey(score.getStuNum())) {
            return false;
        }
        score.setStuNum(++sequence); // 주의할 것! (테스트를 이용해서 잡아내야 하는데, 그 초기값 때문에 조심해야함!)
        scoreMap.put(score.getStuNum(), score);
        System.out.println(findAll());
        return true;
    }

    /*■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■*/

    @Override
    public boolean deleteByStuNum(int stuNum) {
        // 학번이 존재하지 않으면 false
        if (!scoreMap.containsKey(stuNum)) return false;
        scoreMap.remove(stuNum);
        return true;
    }

    /*■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■*/

    @Override
    public Score findByStuNum(int stuNum) {
        return scoreMap.get(stuNum);
    }

    /*■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■*/

    @Override
    public boolean update(int stuNum, ScoreRequestDTO dto) {
        if (!scoreMap.containsKey(stuNum)) return false;
        // Score객체 꺼내기
        Score score = scoreMap.get(stuNum);
        // 점수 재설정
        score.changeScore(dto);
        return true;
    }









}
