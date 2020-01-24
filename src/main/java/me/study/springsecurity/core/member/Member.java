package me.study.springsecurity.core.member;

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
@Entity(name = "MEMBER_MASTER")
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_SEQ")
    private Long memberSeq;

    @Column(name = "MEMBER_ID", length = 20, nullable = false)
    private String memberId;

    @Column(name = "MEMBER_NAME", length = 20, nullable = false)
    private String memberName;

    @Column(name = "MEMBER_PASSWORD", length = 20, nullable = false)
    private String memberPassword;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "CREATE_DT", nullable = false)
    private LocalDateTime createDt;

    public Member(Member member) {
        this.memberId = member.memberId;
        this.memberPassword = member.memberPassword;
        this.memberName = member.memberName;
        this.createDt = LocalDateTime.now(ZoneId.of("Asia/Seoul")).withNano(0);
    }
}
