package com.spring.mvc.chap51.service;

import com.spring.mvc.chap51.dto.HealthRequestDTO;
import com.spring.mvc.chap51.entity.Health;
import com.spring.mvc.chap51.repository.HealthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthService {

    private final HealthMapper healthMapper;

    /* 전체조회 */



    /* 등록 */
    public boolean insertHealth(HealthRequestDTO dto) {
        Health health = new Health(dto);
        healthMapper.save(health);
        return true;
    }



    /* 삭제 */

    /* 개별조회 */

    /* 수정 */
}
