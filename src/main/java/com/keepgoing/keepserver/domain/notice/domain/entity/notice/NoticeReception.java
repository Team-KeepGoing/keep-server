package com.keepgoing.keepserver.domain.notice.domain.entity.notice;

import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notice_reception")
public class NoticeReception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private User user;
    @JoinColumn(name = "notice_id", nullable = false)
    @ManyToOne
    private Notice notice;
    @Column(nullable = false)
    private boolean isRead;
    private LocalDateTime read_at;

}
