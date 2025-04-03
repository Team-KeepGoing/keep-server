package com.keepgoing.keepserver.domain.teacher.domain.repository;

import com.keepgoing.keepserver.domain.teacher.domain.entity.Item;
import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Long countByStatus(ItemStatus status);
}
