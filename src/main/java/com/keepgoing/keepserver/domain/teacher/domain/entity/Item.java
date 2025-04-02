package com.keepgoing.keepserver.domain.teacher.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
        품명
    */
    @Column(nullable = false)
    private String item;

    /*
        분류 번호
    */
    @Column(nullable = false)
    private String serial_number;

    /*
        취득 일자
    */
    @Column(nullable = false)
    private LocalDateTime acquisition_date;

    /*
        취득 단가
    */
    @Column(nullable = false)
    private LocalDateTime price;

    /*
        등록자
    */
    @Column(nullable = false)
    private String register_person;

    /*
        사용 일수
    */
    @Column(nullable = true)
    private LocalDateTime usage_date;

    /*
        메모
    */
    @Column(nullable = true)
    private LocalDateTime memo;

}
