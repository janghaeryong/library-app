package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//Entity 객체와 ID 가 들어간다.
public interface UserRepository extends JpaRepository<User, Long> {
    //exists 존재유무 반환 bool
    //find 하나만
    //findAll 전부

    //By 뒤에
    // GreaterThan : 초과
    // GreaterThanEqual : 이상
    // LessThan 마먼
    // LessThanEqual : 이하
    // Between  ~ 사이
    // StartsWith ~ 시작하는
    // EndsWith ~ 끝나는

    Optional<User> findByName(String name);
    //Optional<User> findByName(String name);
    boolean existsByName(String name);
    void deleteByName(String name);

    long countByAge(Integer age);
    //findByNameOrAge AndAge
}
