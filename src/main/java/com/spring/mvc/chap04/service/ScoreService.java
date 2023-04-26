package com.spring.mvc.chap04.service;

import com.spring.mvc.chap04.dto.ScoreListResponseDTO;
import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreMapper;
import com.spring.mvc.chap04.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 컨트롤러와 레파지토리 사이에서 비즈니스 로직 처리
// ex) 트랜잭션 처리, 예외처리, dto변환처리
//@RequiredArgsConstructor // final 생성자 자동 주입 처리
@Service // @Component : 빈등록!!!
public class ScoreService {

//    private final ScoreRepository scoreRepository;
    private final ScoreMapper scoreRepository;

    @Autowired
//    public ScoreService(@Qualifier("spring") ScoreRepository scoreRepository) {
    public ScoreService(ScoreMapper scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    /*목록 조회 중간처리*/
//    컨트롤러는 데이터베이스를 통해 성적정보 리스트를 가져오길 원한다.
//    그런데 데이터베이스는 성적정보를 전부 모아서 준다.
//    근데 컨트롤러는 정보를 일부만 받았으면 좋겠다

    // scoreList에서 원하는 정보만 추출하고 이름을 마스킹해서
    // 다시 DTO리스트로 변환해줘야 한다.

//    List<Score> scoreList = scoreRepository.findAll(sort);
//        List<ScoreListResponseDTO> responseDTOList = new ArrayList<>();
//        for (Score s : scoreList) {
//            ScoreListResponseDTO dto
//                  = new ScoreListResponseDTO(
//                          s.getStuNum(),
//                          s.getName(),
//                          s.getAverage(),
//                          s.getGrade()
//            );
//            responseDTOList.add(dto);
//        }

    // 지금은 4개여서 이렇게 쓰지만, 나중에 100개 데이터 중에 70개 달라고 하면.. 이렇게 계속 쓸꺼? 못쓰자뉴
    // 첫번째 방법, 새로운 생성자 만들어서 iter 돌리기
//        List<Score> scoreList = scoreRepository.findAll(sort);
//        List<ScoreListResponseDTO> responseDTOList = new ArrayList<>();
//        for (Score s : scoreList) {
//             ScoreListResponseDTO dto = new ScoreListResponseDTO(s);
//             responseDTOList.add(dto);
//        }
    // 두번째방법, 새로운 생성자 만들어서 스트림 사용! 추출이니까 맵핑!
    public List<ScoreListResponseDTO> getList(String sort) {

    List<Score> scoreList = scoreRepository.findAll(sort);

    List<ScoreListResponseDTO> responseDTOList
            = scoreList.stream()
                        .map(s -> new ScoreListResponseDTO(s)) // score꺼내서 ScoreListResponseDTO에 맵핑!
                        .collect(Collectors.toList());


        return responseDTOList;
        // ctrl + alt + n 하면 바로 return까지 작성 가능..... 와...

}


    /*등록 중간 처리*/
    // 컨트롤러는 나에게 DTO를 줬지만, 레파지토리는 ScoreEntity를 달라고 한다. 내가 변환해야겠네..
    public boolean insertScore(ScoreRequestDTO dto) {
//        Score score = new Score(dto);
//        repository.save(score);

        return scoreRepository.save(new Score(dto));

    }


    /* 삭제 중간 처리 */
    public boolean delete(int stnNum) {
        // 현재는 아무것도 없지만, 나중에 게시물 삭제요청 같은거 오면
        // 중간처리 필요함 - 댓글 먼저 지우고, 첨부파일 지우고, 게시글 지우기
        return scoreRepository.deleteByStuNum(stnNum);
    }

    /* 상세조회, 수정화면 조회 중간처리 */
    public Score retrieve(int stuNum) {
        // 만약에 스코어 전체데이터말고 몇개만 추리고 전후처리해서 줘라
        return scoreRepository.findByStuNum(stuNum);
    }


}
