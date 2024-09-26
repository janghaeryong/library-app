package com.group.libraryapp.repository.user;


import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//SQL을 사용해 실제 DB 와의 통신을 담당

@Repository
public class UserJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(String name, int age){
        String sql = "insert into tb_user (name, age) values (?, ?)";
        jdbcTemplate.update(sql, name, age);
    }

    public List<UserResponse>  getUserList(){
        String sql = "select * from tb_user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");

            return new UserResponse(id, name, age);
        });
    }

    public void updateUserName(String name, long id){
        System.out.println("수정 : " + id + "/" + name);
        String sql = "update tb_user set name = ? where id = ?";
        jdbcTemplate.update(sql, name, id);
    }

    public void deleteUserName(String name){
        String sql = "delete from tb_user where name = ?";
        jdbcTemplate.update(sql, name);
    }

    public boolean isUserNotExist( long id) {
        String readSql = "select * from tb_user where id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public boolean isUserNotExist(String name) {
        String readSql = "select * from tb_user where name = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
    }

}
