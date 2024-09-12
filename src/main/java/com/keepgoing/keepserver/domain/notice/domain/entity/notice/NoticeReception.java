package com.keepgoing.keepserver.domain.notice.domain.entity.notice;

import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notice_reception")
public class NoticeReception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private User user;
    @JoinColumn(name = "notice_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private Notice notice;
    @Column(nullable = false)
    private boolean isRead;
    private LocalDateTime read_at;

}
