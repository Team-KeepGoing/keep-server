package com.keepgoing.keepserver.domain.device.entity;

import com.keepgoing.keepserver.domain.device.enums.DeviceStatus;
import com.keepgoing.keepserver.domain.user.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static com.keepgoing.keepserver.domain.device.enums.DeviceStatus.AVAILABLE;

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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceStatus status = AVAILABLE;

    /*
        기기 등록일
    */
    @Column(nullable = false)
    private String regDate;

    /*
        기기 대여 시작일
    */
    @Column
    private LocalDateTime rentDate;

    /*
        대여자 id
    */
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;

    @Builder
    public Device(String deviceName, DeviceStatus status, String imgUrl, String regDate, LocalDateTime rentDate, User borrower) {
        this.deviceName = deviceName;
        this.status = status;
        this.imgUrl = imgUrl;
        this.regDate = regDate;
        this.rentDate = rentDate;
        this.borrower = borrower;
    }
}

