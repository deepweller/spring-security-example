package me.study.springsecurity.core.member;


import lombok.RequiredArgsConstructor;
import me.study.springsecurity.infrastructure.member.MemberJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    public Member getMemberInfoBySeq(long seq) throws Exception {
        Optional<Member> member = memberJpaRepository.findById(seq);
        return member.orElseThrow(() -> new Exception("no data"));
    }

    public List<Member> getMember() {
        return Optional.ofNullable(memberJpaRepository.findAll())
                .orElse(new ArrayList<>());
    }

    public void setMember(Member member) {
        memberJpaRepository.save(new Member(member));
    }
}
