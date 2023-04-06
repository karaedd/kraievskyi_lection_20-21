package com.kraievskyi.task.controller;

import com.kraievskyi.task.dto.BookRequestDto;
import com.kraievskyi.task.dto.BookResponseDto;
import com.kraievskyi.task.dto.BookTextDto;
import com.kraievskyi.task.model.Book;
import com.kraievskyi.task.repository.UserRepository;
import com.kraievskyi.task.service.BookService;
import com.kraievskyi.task.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    BookResponseDto create(@ModelAttribute("book") BookRequestDto bookRequestDto,
                           HttpServletResponse response) throws IOException {
        bookService.save(bookRequestDto);
        response.sendRedirect("/book");
        return null;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    BookTextDto findBook(@PathVariable String id) {return bookService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    List<BookResponseDto> findAll() {
        return bookService.findAll();

    }
}
