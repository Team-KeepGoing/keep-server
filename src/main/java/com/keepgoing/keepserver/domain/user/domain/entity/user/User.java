package com.keepgoing.keepserver.domain.user.domain.entity.user;

import com.keepgoing.keepserver.domain.notice.domain.entity.notice.Notice;
import com.keepgoing.keepserver.domain.notice.domain.entity.notice.NoticeReception;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
        이메일
    */
    @Column(nullable = false)
    private String email;

    /*
        패스워드
    */
    @Column(nullable = false)
    private String password;

    /*
        이름
    */
    @Column(nullable = false)
    private String name;

    /*
        교사 여부
    */
    @Column(nullable = false)
    private boolean teacher;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Notice> notices;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<NoticeReception> noticeReceptions;

    public static User registerUser(
            String email,
            String password,
            String name,
            boolean teacher
    ) {
        User user = new User();
        user.email = email;
        user.password = password;
        user.name = name;
        user.teacher = teacher;

        return user;
    }

    public void fixUserData(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
