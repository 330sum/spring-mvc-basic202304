package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonSpringRepository {

    // 스프링 JDBC 활용 - 데이터베이스 접속 설정정보를 설정 파일(application.properties)을 통해 불러와서 사용
    private final JdbcTemplate jdbcTemplate; //application.properties의 객체를 만드는 것과 같은 것!

    // 저장기능
    public void savePerson(Person p) {
        String sql = "INSERT INTO person (person_name, person_age) VALUES (?, ?)";
        jdbcTemplate.update(sql, p.getPersonName(), p.getPersonAge());
    }

    // 삭제기능
    public void removePerson(long id) {
        String sql = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // 수정기능
    public boolean modify(Person p) {
        String sql = "UPDATE person SET person_name=?, person_age=? WHERE id = ?";
        int result = jdbcTemplate.update(sql,
                p.getPersonName(),
                p.getPersonAge(),
                p.getId());
        return result == 1;
    }

    // 전체 조회 기능
    public List<Person> findAll() {
        String sql = "SELECT * FROM person";
        List<Person> personList = jdbcTemplate.query(sql, new RowMapper() {
            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Person p = new Person();
//                p.setId(rs.getLong("id"));
//                p.setPersonName(rs.getString("person_name"));
//                p.setPersonAge(rs.getInt("person_age"));
                Person p = new Person(rs);
                return p;
            }
        });
            return personList;
            // 람다하면 두줄로 만들 수 있음..... 허헣ㅎ
    }


    // 개별조회
    public Person findOnd(long id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.queryForObject( // 변수 인스턴스화
                sql, // sql
                (rs, n) -> new Person(rs) // RowMapper
                , id); // 물음표 채우기
    }
}
