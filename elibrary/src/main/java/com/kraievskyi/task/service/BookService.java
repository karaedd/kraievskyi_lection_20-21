package com.kraievskyi.task.service;

import com.kraievskyi.task.dto.BookRequestDto;
import com.kraievskyi.task.dto.BookResponseDto;
import com.kraievskyi.task.dto.BookTextDto;

import java.util.List;

public interface BookService {
    BookResponseDto save(BookRequestDto bookRequestDto);

    void addBookToUser(String bookId, String userId);

    List<BookResponseDto> findAll();

    BookTextDto findById(String id);

    BookResponseDto updateBook(BookRequestDto requestDto, String id);

    void delete(String id);
}
