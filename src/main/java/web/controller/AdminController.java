package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }

    @GetMapping("/{id}")
    public String getUserInfoByName(@PathVariable("id") long id, Model model) {
//        User user = userService.getUserByName(principal.getName());
//        model.addAttribute("user", user);
        model.addAttribute(userService.getUser(id));
        return "user/show";
    }

//    @GetMapping("/{id}")
//    public String showUserInfo(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userService.getUser(id));
//        return "user/show";
//    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user, @RequestParam("roles") List<String> rolesId) {
        user.setRoles(roleService.getSetOfRoles(rolesId));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id,
                             @RequestParam("roles") List<String> rolesId) {
        user.setRoles(roleService.getSetOfRoles(rolesId));
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
}
