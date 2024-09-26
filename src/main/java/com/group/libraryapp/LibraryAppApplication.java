package com.group.libraryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



//JDK(자바 개발 도구,JRE+  컴파일러, 디버그 도구등) ->
//JRE (Java RunTime Environment 실행을 위한 도구) ->
//JVM (자바 가상 머신 바이너리 코드를 읽고 검증 실행 ( 각 OS 에 맞게 0, 1 컴퓨터 언어로 해석))

@SpringBootApplication
public class LibraryAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryAppApplication.class, args);
  }

}
