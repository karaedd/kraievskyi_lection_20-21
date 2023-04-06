package com.kraievskyi.task.service.mapper;

import com.kraievskyi.task.dto.UserRequestDto;
import com.kraievskyi.task.dto.UserResponseDto;
import com.kraievskyi.task.model.Book;
import com.kraievskyi.task.model.UserData;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserResponseDto toUserResponseDto(UserData user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setRole(String.valueOf(user.getRole()));
        userResponseDto.setBook(user.getBook()
                .stream()
                .map(Book::getId)
                .collect(Collectors.toList()));
        return userResponseDto;
    }

    public UserData toModel(UserRequestDto userRequestDto) {
        UserData user = new UserData();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }
}
