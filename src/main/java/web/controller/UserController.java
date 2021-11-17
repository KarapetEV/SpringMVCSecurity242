package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getUserInfoByName(@PathVariable("id") long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user/showForUser";
    }
}
