package com.spring.mvc.chap51.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap51.entity.Pscore;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class PscoreRepositoryImpl implements PscoreRepository {

    private static final HashMap<Integer, Pscore> scoreMap;
    private static int sequence;

    static {
        scoreMap = new HashMap<>();
        Pscore s1 = new Pscore("곰이", 99, 88, 77, ++sequence, 0, 0, Grade.A);
        Pscore s2 = new Pscore("오리", 88, 55, 100, ++sequence, 0, 0, Grade.A);
        Pscore s3 = new Pscore("토끼", 100, 77, 88, ++sequence, 0, 0, Grade.A);

        scoreMap.put(s1.getStuNum(), s1);
    }



    @Override
    public List<Pscore> findAll() {
        return null;
    }

    @Override
    public List<Pscore> findAll(String sort) {
        return null;
    }

    @Override
    public boolean register(Score score) {
        return false;
    }

    @Override
    public boolean remove(int stuNum) {
        return false;
    }

    @Override
    public Score detail(int stuNum) {
        return null;
    }

    @Override
    public boolean update(int stuNum) {
        return false;
    }
}
