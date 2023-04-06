package com.kraievskyi.task.service;

import com.kraievskyi.task.dto.BookResponseDto;
import com.kraievskyi.task.dto.UserRequestDto;
import com.kraievskyi.task.dto.UserResponseDto;
import com.kraievskyi.task.model.Book;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

public interface UserService {

    void createUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getById(String id);

    UserResponseDto updateUser(String id, UserRequestDto userRequestDto);

    List<BookResponseDto> findBook(UserDetails userDetails);

    void deleteById(String id);
    public List<Book> findUsersBook();
}
