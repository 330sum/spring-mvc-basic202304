package com.spring.mvc.chap51.repository;

import com.spring.mvc.chap51.dto.HealthRequestDTO;
import com.spring.mvc.chap51.entity.Gender;
import com.spring.mvc.chap51.entity.Health;
import com.spring.mvc.chap51.entity.Obesity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HealthRepositoryImpl implements HealthRepository{

    private static final Map<Integer,Health> healthMap;
    private static int sequence;

    static {
        healthMap = new HashMap<>();
        Health h1 = new Health(new HealthRequestDTO("용가리", Gender.MALE, 22, 190, 85));
        Health h2 = new Health(new HealthRequestDTO("뚜또", Gender.FEMALE, 20, 160, 45));
        Health h3 = new Health(new HealthRequestDTO("레고", Gender.MALE, 21, 180, 80));

        h1.setMemNum(sequence++);
        h2.setMemNum(sequence++);
        h3.setMemNum(sequence++);

        healthMap.put(h1.getMemNum(), h1);
        healthMap.put(h2.getMemNum(), h2);
        healthMap.put(h3.getMemNum(), h3);

    }

    @Override
    public List<Health> findAll() {
//        List<Health> health = new ArrayList<>();

        return null;
    }

    @Override
    public boolean save(Health health) {
        return false;
    }

    @Override
    public boolean remove(int memNum) {
        return false;
    }

    @Override
    public Health findByMemNum(int memNum) {
        return null;
    }

    @Override
    public boolean update(int memNum) {
        return false;
    }
}
