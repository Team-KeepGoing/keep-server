package com.keepgoing.keepserver.domain.device.entity.device;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
        기기 이름
    */
    @Column(nullable = false)
    private String device_name;

    /*
        기기 종류
    */
    @Column(nullable = false)
    private String category;

    /*
        기기 등록일
    */
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime registration_date;

    /*
        기기 대여 상태
    */
    @Column(nullable = false)
    private int status;  /* 1 대여 가능, 2 대여 불가능 */
}
