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
    @Column(name = "item", nullable = false)
    private String item;

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
    private String price;

    /*
        등록자
    */
    @Column(name = "register_person", nullable = false)
    private String registerPerson;

    /*
        사용 일수
    */
    @Column(name = "usage_date", nullable = true)
    private Long usage_date;

    /*
        메모
    */
    @Column(name = "memo", nullable = true)
    private String memo;

    @Builder
    public Item(String item, String serialNumber, LocalDateTime acquisitionDate, String price, String registerPerson, Long usageDate, String memo) {
        this.item = item;
        this.serialNumber = serialNumber;
        this.acquisitionDate = acquisitionDate;
        this.price = price;
        this.registerPerson = registerPerson;
        this.usage_date = usageDate;
        this.memo = memo;
    }

}
