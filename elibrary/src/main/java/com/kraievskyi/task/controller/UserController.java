package com.kraievskyi.task.controller;

import com.kraievskyi.task.dto.UserRequestDto;
import com.kraievskyi.task.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createUser(@ModelAttribute("user") UserRequestDto userRequestDto,
                             HttpServletResponse response) throws IOException {

        userService.createUser(userRequestDto);
        response.sendRedirect("/");
        return null;
    }
}
