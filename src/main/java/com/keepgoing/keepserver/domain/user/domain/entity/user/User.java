package com.keepgoing.keepserver.domain.user.domain.entity.user;

import com.keepgoing.keepserver.domain.notice.domain.entity.notice.Notice;
import com.keepgoing.keepserver.domain.notice.domain.entity.notice.NoticeReception;
import com.keepgoing.keepserver.domain.user.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    /*
        학생 상태
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.NORMAL;

    /*
        학생 상태 수정 시간
     */
    @Column
    private LocalDateTime statusTime;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<NoticeReception> noticeReceptions = new ArrayList<>();

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

    public void fixUserStatus(Status status){
        this.status = status;
        statusTime = LocalDateTime.now();
    }
}
