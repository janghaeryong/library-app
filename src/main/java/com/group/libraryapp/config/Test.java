package com.group.libraryapp.config;

import org.springframework.stereotype.Component;


//콘트롤러 , 서비스, 리포지토리도 아닌데 개발자가 직접 작성한 클래스를 등록할때 사용
@Component
public class Test {
    public Test(){
        System.out.println("Test 생성");
    }
}
