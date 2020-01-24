package me.study.springsecurity.infrastructure.member;

import me.study.springsecurity.core.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Member findByMemberId(String memberId);
}
