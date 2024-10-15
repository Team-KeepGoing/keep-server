package com.keepgoing.keepserver.domain.damage.entity;

import com.keepgoing.keepserver.domain.damage.enums.IssueType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "damage")
public class Damage {
    /*
        id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    /*
        기기 식별 코드 (nfc/이름)
     */
    @Column(nullable = false)
    private String code;

    /*
        문제 유형
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueType issueType;

    /*
        유형에 맞는 자세한 내용
     */
    @Lob
    @Column(nullable = true, columnDefinition = "MEDIUMTEXT")
    private String description;

    /*
        신고 날짜
     */
    @Column(nullable = false)
    private LocalDateTime reportDate;

    @Builder
    public Damage(Long idx, String code, String issueType, String description, LocalDateTime reportDate) {
        this.idx = idx;
        this.code = code;
        this.issueType = IssueType.find(issueType);
        this.description = description;
        this.reportDate = reportDate;
    }
}
