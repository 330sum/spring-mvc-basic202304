package com.spring.mvc.chap04.service;

import com.spring.mvc.chap04.dto.ScoreListResponseDTO;
import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 컨트롤러와 레파지토리 사이에서 비즈니스 로직 처리
// ex) 트랜잭션 처리, 예외처리, dto변환처리
@RequiredArgsConstructor // final 생성자 자동 주입 처리
@Service // @Component : 빈등록!!!
public class ScoreService {

    private final ScoreRepository scoreRepository;


    /*목록 조회 중간처리*/
//    컨트롤러는 데이터베이스를 통해 성적정보 리스트를 가져오길 원한다.
//    그런데 데이터베이스는 성적정보를 전부 모아서 준다.
//    근데 컨트롤러는 정보를 일부만 받았으면 좋겠다

    // scoreList에서 원하는 정보만 추출하고 이름을 마스킹해서
    // 다시 DTO리스트로 변환해줘야 한다.

    public List<ScoreListResponseDTO> getList(String sort) {

    List<Score> scoreList = scoreRepository.findAll(sort);

    List<ScoreListResponseDTO> responseDTOList
            = scoreList.stream()
                        .map(s -> new ScoreListResponseDTO(s))
                        .collect(Collectors.toList());

//        List<ScoreListResponseDTO> responseDTOList = new ArrayList<>();
//        for (Score s : scoreList) {
//            ScoreListResponseDTO dto = new ScoreListResponseDTO(s);
//            responseDTOList.add(dto);
//        }

        return responseDTOList;
        // ctrl + alt + n 하면 바로 return까지 작성 가능..... 와...

}


    /*등록 중간 처리*/
    // 컨트롤러는 나에게 DTO를 줬지만, 레파지토리는 ScoreEntity를 달라고 한다.
    public boolean insertScore(ScoreRequestDTO dto) {
//        Score score = new Score(dto);
//        repository.save(score);

        return scoreRepository.save(new Score(dto));

    }


    /* 삭제 중간 처리 */
    public boolean delete(int stnNum) {
        return scoreRepository.deleteByStuNum(stnNum);
    }

    /* 상세조회, 수정화면 조회 중간처리 */
    public Score retrieve(int stuNum) {
        // 만약에 스코어 전체데이터말고 몇개만 추리고 전후처리해서 줘라
        return scoreRepository.findByStuNum(stuNum);
    }


}
