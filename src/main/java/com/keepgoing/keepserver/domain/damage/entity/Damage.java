package com.keepgoing.keepserver.domain.damage.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private Long id;

    /*
        기기 식별 코드 (nfc/이름)
     */
    @Column(nullable = false)
    private String code;

    /*
        문제 유형
     */
    @Column(nullable = false)
    private String issueType;

    /*
        유형에 맞는 자세한 내용
     */
    @Lob
    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String description;

    /*
        신고 날짜
     */
    @Column(nullable = false)
    private LocalDateTime reportDate;

}
