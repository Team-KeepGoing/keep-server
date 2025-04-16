package com.keepgoing.keepserver.global.file.mapper;

import org.apache.poi.ss.usermodel.Row;

public interface RowMapper<T> {
    T map(Row row);
}