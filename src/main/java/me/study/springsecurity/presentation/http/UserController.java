package me.study.springsecurity.presentation.http;


import lombok.RequiredArgsConstructor;
import me.study.springsecurity.core.user.User;
import me.study.springsecurity.core.user.UserService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("{seq}")
    public User getUserInfoById(@PathVariable("seq") long seq) {
        try {
            return userService.getUserInfoBySeq(seq);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping
    public void setUser(@RequestBody User user) {
        userService.setUser(user);
    }
}
