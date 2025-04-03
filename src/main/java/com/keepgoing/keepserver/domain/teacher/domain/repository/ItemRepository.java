package com.keepgoing.keepserver.domain.teacher.domain.repository;

import com.keepgoing.keepserver.domain.teacher.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
