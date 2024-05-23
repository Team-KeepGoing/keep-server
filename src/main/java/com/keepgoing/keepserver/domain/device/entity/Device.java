package com.keepgoing.keepserver.domain.device.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    private String deviceName;

    /*
        기기 사진
    */
    @Column(nullable = false)
    private String imgUrl;

    /*
        기기 대여 상태
    */
    @Column(nullable = false)
    private int status;  /* 1 대여 가능, 2 대여 불가능 */

    @Builder
    public Device(String deviceName, int status, String imgUrl) {
        this.deviceName = deviceName;
        this.status = status;
        this.imgUrl = imgUrl;
    }
}