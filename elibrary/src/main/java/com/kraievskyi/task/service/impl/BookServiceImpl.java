package com.kraievskyi.task.service.impl;

import com.kraievskyi.task.dto.BookRequestDto;
import com.kraievskyi.task.dto.BookResponseDto;
import com.kraievskyi.task.dto.BookTextDto;
import com.kraievskyi.task.model.Book;
import com.kraievskyi.task.model.UserData;
import com.kraievskyi.task.repository.BookRepository;
import com.kraievskyi.task.repository.UserRepository;
import com.kraievskyi.task.service.BookService;
import com.kraievskyi.task.service.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponseDto save(BookRequestDto bookRequestDto) {
        return bookMapper.toBookResponseDto(bookRepository.save(
                bookMapper.toModel(bookRequestDto)));
    }

    @Override
    public void addBookToUser(String bookId, String userId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        UserData user = userRepository.findById(userId).orElseThrow();
        user.getBook().add(book);
        userRepository.save(user);
    }

    @Override
    public List<BookResponseDto> findAll() {
        return bookRepository.findAll().stream().map(
                bookMapper::toBookResponseDto).collect(Collectors.toList());
    }

    @Override
    public BookTextDto findById(String id) {
        BookTextDto bookTextDto = new BookTextDto();
        Book book = bookRepository.findById(id).orElseThrow();
        bookTextDto.setText(book.getText());
        return bookTextDto;
        //return bookMapper.toBookResponseDto(bookRepository.findById(id).orElseThrow());
    }

    @Override
    public BookResponseDto updateBook(BookRequestDto requestDto, String id) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setText(requestDto.getText());
        return bookMapper.toBookResponseDto(book);
    }

    @Override
    public void delete(String id) {
        bookRepository.deleteById(id);
    }
}
