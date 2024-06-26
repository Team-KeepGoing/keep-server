package com.keepgoing.keepserver.domain.book.repository;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.user.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByNfcCode(String NfcCode);
    Optional<Book> findBookByNfcCodeContaining(String NfcCode);
    Optional<Book> findByBookName(String bookName);
    List<Book> findByBorrower(User borrower);
}
