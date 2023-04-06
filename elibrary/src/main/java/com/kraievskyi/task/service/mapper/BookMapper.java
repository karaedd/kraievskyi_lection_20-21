package com.kraievskyi.task.service.mapper;

import com.kraievskyi.task.dto.BookRequestDto;
import com.kraievskyi.task.dto.BookResponseDto;
import com.kraievskyi.task.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toModel(BookRequestDto requestDto) {
        Book book = new Book();
        book.setText(requestDto.getText());
        book.setAuthor(requestDto.getAuthor());
        book.setTitle(requestDto.getTitle());
        return book;
    }

    public BookResponseDto toBookResponseDto(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setText(book.getText());
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setAuthor(book.getAuthor());
        return bookResponseDto;
    }
}
