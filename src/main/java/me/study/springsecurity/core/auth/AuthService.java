package me.study.springsecurity.core.auth;

import lombok.RequiredArgsConstructor;
import me.study.springsecurity.core.member.Member;
import me.study.springsecurity.infrastructure.member.MemberJpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService implements UserDetailsService {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = Optional.ofNullable(memberJpaRepository.findByMemberId(memberId))
                .orElseThrow(() -> new UsernameNotFoundException("not exist member"));

        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("admin".equals(memberId)) {
            authorities.add(new SimpleGrantedAuthority(AuthRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(AuthRole.USER.getValue()));
        }

        return new User(member.getMemberId(), member.getMemberPassword(), authorities);
    }

    @Transactional
    public Long joinMember(Member member) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setMemberPassword(passwordEncoder.encode(member.getMemberPassword()));

        return memberJpaRepository.save(member).getMemberSeq();
    }
}
