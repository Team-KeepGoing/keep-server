package com.keepgoing.keepserver.domain.damage.enums;

import com.keepgoing.keepserver.global.exception.damage.DamageError;
import com.keepgoing.keepserver.global.exception.damage.DamageException;

import java.util.Arrays;

public enum IssueType {
    COVER_DAMAGE("표지 손상"),
    PAGE_ISSUE("페이지 관련 문제"),
    BOOK_BODY_DAMAGE("책 본체 손상"),
    TEXT_PRINT_ISSUE("텍스트 및 인쇄 문제"),
    ENVIRONMENTAL_DAMAGE("환경적 손상"),
    SCREEN_ISSUE("화면 관련 문제"),
    CONNECTIVITY_ISSUE("연결성 문제"),
    BATTERY_POWER_ISSUE("배터리 및 전원 문제"),
    SOUND_ISSUE("음향 문제"),
    EXTERNAL_DAMAGE("외부 파손"),
    OTHER("기타");

    private final String issue;

    IssueType(String issue) {
        this.issue = issue;
    }

    public static IssueType find(String issue) {
        return Arrays.stream(IssueType.values())
                .filter(issueType -> issueType.issue.equals(issue))
                .findAny()
                .orElseThrow(() -> new DamageException(DamageError.INVALID_ISSUE_TYPE));
    }
}
