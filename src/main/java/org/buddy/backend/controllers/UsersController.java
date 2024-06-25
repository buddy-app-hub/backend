package org.buddy.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.buddy.backend.models.User;
import org.buddy.backend.services.UserService;

@RestController
@RequestMapping("buddies")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public User getUser(@RequestParam String name) {
        return userService.getUserByName(name);
    }
}
