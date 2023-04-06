package com.kraievskyi.task.service.impl;

import com.kraievskyi.task.dto.BookResponseDto;
import com.kraievskyi.task.dto.UserRequestDto;
import com.kraievskyi.task.dto.UserResponseDto;
import com.kraievskyi.task.model.Book;
import com.kraievskyi.task.model.UserData;
import com.kraievskyi.task.model.Role;
import com.kraievskyi.task.repository.UserRepository;
import com.kraievskyi.task.service.UserService;
import com.kraievskyi.task.service.mapper.BookMapper;
import com.kraievskyi.task.service.mapper.UserMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BookMapper bookMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
    }


    @Override
    @Transactional
    public void createUser(UserRequestDto userRequestDto) {
        UserData user = userMapper.toModel(userRequestDto);
        user.setPassword("{noop}" + userRequestDto.getPassword());
        user.setRole(Role.valueOf("USER"));
        user.setBook(new ArrayList<>());
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserData user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    @Override
    public List<Book> findUsersBook() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();
        UserData userData;
        List<Book> book = new ArrayList<>();
        if (principal instanceof User user) {
            userData = userRepository.findUserByEmail(user.getUsername());
            book = userData.getBook();
        }
        return book;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getById(String id) {
        return userMapper.toUserResponseDto(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserResponseDto updateUser(String id, UserRequestDto userRequestDto) {
        UserData user = userRepository.findById(id).orElseThrow();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public List<BookResponseDto> findBook(UserDetails userDetails) {
        String userId = ((UserData) userDetails).getId();
        UserData user = userRepository.findById(userId).orElseThrow();
        List<Book> bookList = user.getBook();
        return bookList.stream().map(bookMapper::toBookResponseDto).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
