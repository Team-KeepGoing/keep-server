package com.keepgoing.keepserver.domain.book.repository;

import com.keepgoing.keepserver.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByNfcCode(String NfcCode);
    List<Book> findByBookNameContaining(String bookName);
    Optional<Book> findByBookName(String bookName);
}
