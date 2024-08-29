package com.keepgoing.keepserver.domain.book.domain.repository;

import com.keepgoing.keepserver.domain.book.domain.entity.Book;
import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByNfcCode(String NfcCode);
    Optional<Book> findBookByNfcCodeContaining(String NfcCode);
    List<Book> findByBorrower(User borrower);
    List<Book> findByBorrowerAndRentDateBetween(User borrower, LocalDateTime startDate, LocalDateTime endDate);
}
