package com.keepgoing.keepserver.global.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateRange {
    private final LocalDateTime st;
    private final LocalDateTime end;

    public DateRange(LocalDateTime st, LocalDateTime end) {
        this.st = st;
        this.end = end;
    }

    public static DateRange fromDateString(String dateString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return new DateRange(startOfDay, endOfDay);
    }
    public LocalDateTime getStart() {
        return st;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}

