package com.keepgoing.keepserver.domain.file.service.mapper;

import org.apache.poi.ss.usermodel.Row;

public interface RowMapper<T> {
    T map(Row row);
}
