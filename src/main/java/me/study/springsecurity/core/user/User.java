package me.study.springsecurity.core.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_SEQ")
    private Long userSeq;

    @Column(name = "USER_ID", length = 20, nullable = false)
    private String userId;

    @Column(name = "USER_NAME", length = 20, nullable = false)
    private String userName;

    @Column(name = "USER_PASSWORD", length = 20, nullable = false)
    private String userPassword;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "CREATE_DT", nullable = false)
    private LocalDateTime createDt;

    public User(User user) {
        this.userId = user.userId;
        this.userPassword = user.userPassword;
        this.userName = user.userName;
        this.createDt = LocalDateTime.now(ZoneId.of("Asia/Seoul")).withNano(0);
    }
}
