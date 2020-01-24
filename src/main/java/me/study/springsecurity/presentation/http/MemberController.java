package me.study.springsecurity.presentation.http;


import lombok.RequiredArgsConstructor;
import me.study.springsecurity.core.member.Member;
import me.study.springsecurity.core.member.MemberService;
import me.study.springsecurity.presentation.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("{seq}")
    public Member getMemberInfoById(@PathVariable("seq") long seq) {
        try {
            return memberService.getMemberInfoBySeq(seq);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping
    public List<Member> getMember() {
        return memberService.getMember();
    }

    @PostMapping
    public void setMember(@RequestBody MemberDto memberDto) {
        memberService.setMember(memberDto);
    }
}
