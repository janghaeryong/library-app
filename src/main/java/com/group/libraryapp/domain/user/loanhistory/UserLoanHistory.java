package com.group.libraryapp.domain.user.loanhistory;


import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity(name = "tb_user_loan_history")
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id = null;

    //기존
    //@Column(nullable = false, name = "user_id")
    //Long userId;

    @ManyToOne //내가 다수 이고 넌 하나다.() (대출 기록은 여러게 이고 소유자는 사용자는 하나다!) (N:1 관계)
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false, name = "book_name")
    String bookName;

    @Column(nullable = false, name = "is_return")
    Boolean isReturn = false;

    protected UserLoanHistory(){}

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public Long getId() {
        return id;
    }
    public Long getUserId() {
        return user.getId();
    }

    public String getBookName() {
        return this.bookName;
    }


    public void doReturn(){
        this.isReturn = true;
    }
    public void doLoan(){
        this.isReturn = false;
    }

    public Boolean getReturn() {
        return isReturn;
    }
}
