package com.keepgoing.keepserver.domain.notice.domain.entity.notice;

import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;
    @Lob
    @Column(nullable = false)
    private String message;
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;
    @Column(nullable = false)
    private char isGlobalYN;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    @Column
    private LocalDateTime editTime;
}
