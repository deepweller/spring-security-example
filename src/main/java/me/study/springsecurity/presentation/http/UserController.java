package me.study.springsecurity.presentation.http;


import lombok.RequiredArgsConstructor;
import me.study.springsecurity.core.user.User;
import me.study.springsecurity.core.user.UserService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @GetMapping("{id}")
    public User getUserInfoById(@PathVariable("id") long id) {
        try {
            return userService.getUserInfoById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping
    public void setUser(@RequestBody String name) {
        userService.setUser(name);
    }
}
