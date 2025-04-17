package com.keepgoing.keepserver.domain.teacher.domain.entity;

import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemUpdateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

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
    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;

    /*
        취득 일자
    */
    @Column(name = "acquisition_date", nullable = false)
    private LocalDate acquisitionDate;

    /*
        취득 단가
    */
    @Column(name = "price", nullable = false)
    private Long price;

    /*
        사용자
    */
    @Column(name = "rented_by", nullable = true)
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
    private LocalDate returnDate;

    /*
        대여일
    */
    @Column(name = "rental_date", nullable = true)
    private LocalDate rentalDate;

    /*
        사용 일수
    */
    @Column(name = "usage_date", nullable = true)
    private Long usageDate;

    /*
        기기 상태
     */

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ItemStatus status = ItemStatus.AVAILABLE;

    @Builder
    public Item(String item, String details, String serialNumber, LocalDate acquisitionDate, Long price, String rentedBy, String place, LocalDate returnDate, LocalDate rentalDate, Long usageDate, ItemStatus status) {
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

    public void updateItem(ItemUpdateRequest dto) {
        if (dto.item() != null) {
            this.item = dto.item();
        }
        if (dto.details() != null) {
            this.details = dto.details();
        }
        if (dto.serialNumber() != null) {
            this.serialNumber = dto.serialNumber();
        }
        if (dto.acquisitionDate() != null) {
            this.acquisitionDate = dto.acquisitionDate();
        }
        if (dto.price() != null) {
            this.price = dto.price();
        }
        if (dto.rentedBy() != null) {
            this.rentedBy = dto.rentedBy();
        }
        if (dto.place() != null) {
            this.place = dto.place();
        }
        if (dto.returnDate() != null) {
            this.returnDate = dto.returnDate();
        }
        if (dto.rentalDate() != null) {
            this.rentalDate = dto.rentalDate();
        }
        if (dto.usageDate() != null) {
            this.usageDate = dto.usageDate();
        }
        if (dto.status() != null) {
            this.status = dto.status();
        }
    }

}
