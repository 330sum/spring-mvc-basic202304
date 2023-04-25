package com.spring.mvc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration // 자바의 객체가 아니라, 설정파일이라는 것을 알려주는 어노테이션. 예전에 xml을 사용했는데
        // 마크업문서는 에러잡기가 어려워서 자바파일 씀
public class DatabaseConfig {

   /* Datasource 설정 : 데이터베이스 연결 정보
   *  아래의 4개 설정을 데이터소스라고 함! */
    // URL: DBMS(오라클,mysql 등)가 설치된 경로
    // USERNAME: DB 계정명
    // PASSWORD: DB password
    // DRIVER CLASS: DBMS마다 설치한 커넥터 드라이버

    /* 커넥션 풀 설정 (object pool pattern)*/
    // 커넥션 풀 설정
    //  : DB 접속 시 사용하는 리소스를 관리하는 프로그램
    // 커넥션 풀 라이브러리가 많은데 히카리cp가 통일함.. 그래서 스프링에서 HikariCP를 자동으로 깔아둠
    // 그래서 우린 설정만 하면 두개의 설정을 같이 할 수 있음


//    @Bean
//    public DataSource dataSource() {
//        // 히카리 설정
//        HikariConfig config = new HikariConfig();
//        config.setUsername("root"); // 오라클은 sys
//        config.setPassword("1234");
//        config.setJdbcUrl("jdbc:mariadb://localhost:3306/spring"); // 우리가 만들어둔 Database이름이 spring이였음
//        config.setDriverClassName("org.mariadb.jdbc.Driver");
//
//        return new HikariDataSource(config);
//    }

    // 스프링부트 없으면 이렇게 설정해야함.

    // 근데 스프링부트에서 설정 간편하게 지원해줌!
    // src -> main -> resources -> application.properties

}
