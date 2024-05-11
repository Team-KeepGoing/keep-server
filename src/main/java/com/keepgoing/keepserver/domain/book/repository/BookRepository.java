package com.keepgoing.keepserver.domain.book.repository;

import com.keepgoing.keepserver.domain.book.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public Book findBookByNfcCode(String NfcCode);

}
