package com.kraievskyi.task.controller;

import com.kraievskyi.task.dto.BookRequestDto;
import com.kraievskyi.task.dto.UserRequestDto;
import com.kraievskyi.task.dto.UserResponseDto;
import com.kraievskyi.task.service.BookService;
import com.kraievskyi.task.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final BookService bookService;
    private final UserService userService;
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public String home() {
        return "home";
    }

    @GetMapping("/book")
    @PreAuthorize("hasRole('ADMIN')")
    String findAll(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "show-books";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    String findBookForUser(Model model) {
        model.addAttribute("books", userService.findUsersBook());
        return "users-books";
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    String createUser(Model model) {
       model.addAttribute("user", new UserRequestDto());
       return "users";
    }

    @PostMapping("/add-book-to-user")
    @PreAuthorize("hasRole('ADMIN')")
    public String addBookToUser(@RequestParam("bookId") String bookId,
                                @RequestParam("userId") String userId) {
        log.debug("Adding book {} to user {}", bookId, userId);
        bookService.addBookToUser(bookId, userId);
        return "redirect:/book";
    }

    @GetMapping("/createBook")
    @PreAuthorize("hasRole('ADMIN')")
    String create(Model model) {
        model.addAttribute("book", new BookRequestDto());
        return "create-book";
    }

    @ModelAttribute("users")
    List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
