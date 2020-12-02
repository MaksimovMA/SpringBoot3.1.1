package com.example.demo.web.controller;

import com.example.demo.web.model.Role;
import com.example.demo.web.model.User;
import com.example.demo.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "admin")
    public String Users(ModelMap model) {
        List<User> list = userService.getListUsers();
        model.addAttribute("list", list);
        return "users";
    }

    @PostMapping("admin/add")
    public String newUser(User user) {
        Set<Role> tempRolesSet = new HashSet<>();
        for (Role role : user.getRoles()) {
            tempRolesSet.add(new Role(Long.parseLong(role.getName()), role.getName()));
        }
        user.setRoles(tempRolesSet);
        userService.newUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(path = "admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @RequestMapping(path = {"admin/edit", "admin/edit/{id}"})
    public String editUser(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            User entity = userService.getUserById(id.get());
            model.addAttribute("user", entity);
        } else {
            model.addAttribute("user", new User());
        }
        return "editUser";
    }

    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String createUser(User user) {
        Set<Role> tempRolesSet = new HashSet<>();
        for (Role role : user.getRoles()) {
            tempRolesSet.add(new Role(Long.parseLong(role.getName()), role.getName()));
        }
        user.setRoles(tempRolesSet);
        if (user.getId() == null) {
            userService.newUser(user);
        } else userService.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/update")
    public String editUserForm(ModelMap model, User user) {
        model.addAttribute("user", userService.getUserById(user.getId()));
        return "editUser";
    }

    @PostMapping("admin/update")
    public String editUser(User user) {
        Set<Role> tempRolesSet = new HashSet<>();
        for (Role role : user.getRoles()) {
            tempRolesSet.add(new Role(Long.parseLong(role.getName()), role.getName()));
        }
        user.setRoles(tempRolesSet);
        userService.editUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "user")
    public String userDataGet(ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute(
                "user", userService.getUserById(userService.getId(auth.getName())));
        return "userData";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}