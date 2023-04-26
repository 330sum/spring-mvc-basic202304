package com.spring.mvc.jdbc;

// 이 클래스는 데이터베이스와 테이블과의 연동을 위한 객체 (entity)
// DB table의 컬럼과 1:1로 매칭되는 필드를 적어주세요.
// entity: 디비랑 연동 / DTO: 브라우저랑 연동

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private long id; //int 23억 까지 받을 수 있음, db int(10)이면 99억까지 설정할 수 있음
    private String personName;
    private int personAge;

    public Person(ResultSet rs) throws SQLException {
        this.id = rs.getLong("id");
        this.personName = rs.getString("person_name");
        this.personAge = rs.getInt("person_age");
    }
}
