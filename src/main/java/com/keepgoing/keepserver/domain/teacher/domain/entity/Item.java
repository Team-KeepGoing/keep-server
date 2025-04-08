package com.keepgoing.keepserver.domain.teacher.domain.entity;

import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;
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
    @Column(name = "item", nullable = false)
    private String item;

    /*
        세부 제품명
    */
    @Column(name = "details", nullable = false)
    private String details;

    /*
        분류 번호
    */
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    /*
        취득 일자
    */
    @Column(name = "acquisition_date", nullable = false)
    private LocalDateTime acquisitionDate;

    /*
        취득 단가
    */
    @Column(name = "price", nullable = false)
    private Long price;

    /*
        사용자
    */
    @Column(name = "rentedBy", nullable = true)
    private String rentedBy;

    /*
        기기 위치
    */
    @Column(name = "place", nullable = false)
    private String place;

    /*
        반납일
    */
    @Column(name = "return_date", nullable = true)
    private LocalDateTime returnDate;

    /*
        대여일
    */
    @Column(name = "rental_date", nullable = true)
    private LocalDateTime rentalDate;

    /*
        사용 일수
    */
    @Column(name = "usage_date", nullable = true)
    private Long usageDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ItemStatus status = ItemStatus.AVAILABLE;

    @Builder
    public Item(String item, String details, String serialNumber, LocalDateTime acquisitionDate, Long price, String rentedBy, String place, LocalDateTime returnDate, LocalDateTime rentalDate, Long usageDate, ItemStatus status) {
        this.item = item;
        this.details = details;
        this.serialNumber = serialNumber;
        this.acquisitionDate = acquisitionDate;
        this.price = price;
        this.rentedBy = rentedBy;
        this.place = place;
        this.returnDate = returnDate;
        this.rentalDate = rentalDate;
        this.usageDate = usageDate;
        this.status = status != null ? status : ItemStatus.AVAILABLE;
    }

    public void updateStatus(ItemStatus status) {
        this.status = status;
    }

}
