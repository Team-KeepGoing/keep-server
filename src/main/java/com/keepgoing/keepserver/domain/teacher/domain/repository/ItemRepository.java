package com.keepgoing.keepserver.domain.teacher.domain.repository;

import com.keepgoing.keepserver.domain.teacher.domain.entity.Item;
import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Long countByStatus(ItemStatus status);
    List<Item> findAllBySerialNumberIn(Collection<String> serialNumbers);
}
