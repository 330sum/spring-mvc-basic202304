package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("spring")
@RequiredArgsConstructor
public class ScoreSpringRepository implements ScoreRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Score> findAll() {
        return findAll("num");
    }

    @Override
    public List<Score> findAll(String sort) {
//        String sql = "SELECT * FROM tbl_score";
//        List<Score> scoreList = jdbcTemplate.query(sql, new RowMapper() {
//            @Override
//            public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Score s = new Score(rs);
//                return s;
//            }
//        });
//        return scoreList;

        String sql = "SELECT * FROM tbl_score";
        switch (sort) {
            case "num":
                sql += " ORDER BY stu_num";
                break;
            case "name":
                sql += " ORDER BY stu_name";
                break;
            case "avg":
                sql += " ORDER BY average DESC";
                break;
        }

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Score(rs));

    }

    @Override
    public boolean save(Score s) {
        String sql = "INSERT INTO tbl_score (name, kor, eng, math, total, average, grade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, s.getName(), s.getKor(), s.getEng(), s.getMath(), s.getTotal(), s.getAverage(), String.valueOf(s.getGrade()));
        return result == 1;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        String sql = "DELETE FROM tbl_score WHERE stu_num=?";
        return jdbcTemplate.update(sql, stuNum) == 1;
    }

    @Override
    public Score findByStuNum(int stuNum) {
        String sql = "SELECT * FROM tbl_score WHERE stu_num=?";
        return jdbcTemplate.queryForObject(sql,
                (rs , n) -> new Score(rs)
                , stuNum
        );
    }

    @Override
    public boolean update(int stuNum, ScoreRequestDTO dto) {
        return false;
    }
}
