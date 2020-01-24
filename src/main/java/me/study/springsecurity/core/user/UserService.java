package me.study.springsecurity.core.user;


import lombok.RequiredArgsConstructor;
import me.study.springsecurity.infrastructure.user.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJpaRepository userJpaRepository;

    public User getUserInfoBySeq(long seq) throws Exception {
        Optional<User> user = userJpaRepository.findById(seq);
        return user.orElseThrow(() -> new Exception("no data"));
    }

    public void setUser(User user) {
        userJpaRepository.save(new User(user));
    }

}
