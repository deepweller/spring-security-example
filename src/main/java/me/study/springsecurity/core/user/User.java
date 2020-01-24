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
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NAME", length = 20, nullable = false)
    private String userName;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "CREATE_DT", nullable = false)
    private LocalDateTime createDt;

    public User(String userName) {
        this.userName = userName;
        this.createDt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
