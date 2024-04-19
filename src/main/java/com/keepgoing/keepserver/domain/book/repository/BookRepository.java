package com.keepgoing.keepserver.domain.book.repository;

import com.keepgoing.keepserver.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> FindByName(String name);

    public List<Book> FindByNfc(String nfcCode);
    public List<Book> getAll();
}
