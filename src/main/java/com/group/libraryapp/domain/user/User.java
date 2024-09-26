package com.group.libraryapp.domain.user;


import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Entity 저장되고 관리되어야할 데이터
//Entity 에는 기본 생성자는 무조건 필요하다
// 테이블과 매핑 된 객체를 관리/보관하는 역할
@Entity(name = "tb_user")
public class User {
    protected User() {}

    @Id //해당 필드를 primary key 로 간주
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 생성되는 값이다. // IDENTITY  auto increment
    private Long id = null;

    @Column(nullable = false, length = 20)
    private String name;

    private Integer age;

    //cascade 폭포처럼 흐르다 ( 연관 데이터도 함꼐 삭제 )
    //orphanRemoval
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) //난 하나고 넌 다수다 (1:N)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    public User(String name, Integer age) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다." , name ));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name){
        this.name = name;
    }




    public void loanBook(String bookName){

        UserLoanHistory userLoanHistory = this.userLoanHistories.stream()
                .filter(history-> history.getBookName().equals(bookName))
                .findFirst()
                .orElse(
                    new UserLoanHistory(this, bookName)
                );
        userLoanHistory.doLoan();

    }


    public void returnBook(String bookName){
        UserLoanHistory userLoanHistory = this.userLoanHistories.stream()
                .filter(history-> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        userLoanHistory.doReturn();
    }

}
