package com.group.libraryapp.domain.user.loanhistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

    Optional<UserLoanHistory> findByUser_IdAndBookName(Long userId, String bookName);
    //select * from user_loan_history where book_name = ? and is_return = ?
    boolean existsByBookNameAndIsReturn(String bookId, boolean isReturn);
}
